package com.bct.weeklystatus.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bct.weeklystatus.entities.Account;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.WeekStatus;
import com.bct.weeklystatus.model.InfraData;
import com.bct.weeklystatus.model.IvmsInfraData;
import com.bct.weeklystatus.model.SRData;
import com.bct.weeklystatus.model.TicketData;
import com.bct.weeklystatus.services.AccountService;
import com.bct.weeklystatus.services.ProjectDetailService;
import com.bct.weeklystatus.services.ProjectStatusDetailService;

@RestController
@RequestMapping("/bctdashboard")
//@CrossOrigin(origins = {"http://localhost:4200","http://10.240.8.62:7001","http://localhost:8088"}, allowedHeaders="*")

public class ChartController {
    @Autowired
    private ProjectDetailService projectDetailService;


    @GetMapping("/chartData/getWeeks")
    public List<String> getWeeks()
	{
    	System.out.println("getWeeks");

    	HashSet<String> weeks = new HashSet<String>();
    
        List<ProjectDetail> projectDetailList= projectDetailService.getAllProjectDetails();
        projectDetailList.stream().forEach(pd->{
        pd.getWeekDuration().stream().forEach(wd->{
        	 weeks.add(wd.getWeekduration());
        });
        });
    	System.out.println("weeks ..."+weeks.size());
        Comparator<String> dateCompartor = (lhs, rhs) -> {
    		try {
    			
    			return getStartDate(lhs).compareTo(getStartDate(rhs));
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return 0;
    		}
    	};
        return weeks.stream().collect(Collectors.toCollection(HashSet::new)).stream().sorted(dateCompartor).collect(Collectors.toList());
    	  
         	
    }
       


    @GetMapping("/chartData/getWeeks/{projectId}")
    public List<String> getWeeks(@PathVariable Long projectId)
	{
    	System.out.println("getWeeks for "+projectId);

    	HashSet<String> weeks = new HashSet<String>();
    
        ProjectDetail projectDetail= projectDetailService.getProjectDetail(projectId);
       	projectDetail.getWeekDuration().stream().forEach(wd->{
        	 weeks.add(wd.getWeekduration());
        });
        System.out.println("weeks ..."+weeks.size());
        Comparator<String> dateCompartor = (lhs, rhs) -> {
    		try {
    			
    			return getStartDate(lhs).compareTo(getStartDate(rhs));
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			return 0;
    		}
    	};
    	return weeks.stream().collect(Collectors.toCollection(HashSet::new)).stream().sorted(dateCompartor).collect(Collectors.toList());
    	  
         	
    }
       
	
static Date getStartDate(String weekDuration) throws ParseException {
    String startDateInStr=	weekDuration.split("-")[0];
    DateFormat format = new java.text.SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
    Date date = format.parse(startDateInStr);
	return date;
    			
    }
    
//Angular
    @PostMapping("/chartData/tesla")
	public List<com.bct.weeklystatus.model.TicketData> getChartDataTesla()
	{
    	System.out.println("getChartDataTesla");

    	List<Long> projectIDList = new ArrayList<Long>();
    	projectIDList.add(1200L);
    	//projectIDList.add(500L);
        List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
        List<TicketData> ticketDataList = new ArrayList<TicketData>();
        projectStatusDetailList.forEach(projectStatusDetail->{
        	TicketData ticketData = new TicketData();
        	ticketData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
        	ticketData.setL1IssuesClosed(projectStatusDetail.getL1IssuesClosed());
        	ticketData.setL2IssuesClosed(projectStatusDetail.getL2IssuesClosed());
        	ticketData.setL1IssuesOpened(projectStatusDetail.getL1IssuesOpened());
        	ticketData.setL2IssuesOpened(projectStatusDetail.getL2IssuesOpened());
        	LocalDate day = projectStatusDetail.getDateSelection();
      
        	while (day.getDayOfWeek() != DayOfWeek.MONDAY)
        	 {
        		      day = day.minusDays(1);
        	 }
        	
        	ticketData.setTicketCreatedDate(day);
        	System.out.println(" before reducing "+ticketData.toString());
        	ticketDataList.add(ticketData);       
        	
        });
      
        ticketDataList.stream().forEach(t->System.out.println(t.toString()));
        Map<Optional<LocalDate>,TicketData> mapfTicketsByWeekly =
        	    ticketDataList
        	        .stream()
        	        .collect(
        	            Collectors.groupingBy(e->Optional.ofNullable(e.getTicketCreatedDate()),                      
        	                  Collectors.reducing(
        	                		  new TicketData(LocalDate.MIN,0,0,0,0,""),
        	                		  (t1,t2)->
                	                    new TicketData(t2.getTicketCreatedDate(),t1.getL1IssuesOpened()+t2.getL1IssuesOpened(),t1.getL1IssuesClosed()+t2.getL1IssuesClosed(),
                	                    		t1.getL2IssuesOpened()+t2.getL2IssuesOpened(),t1.getL2IssuesClosed()+t2.getL2IssuesClosed(),t2.getProjectName()))));
        List<TicketData> ticketDataListNew = new ArrayList<TicketData>();
        mapfTicketsByWeekly.forEach((date,t)->{
        	ticketDataListNew.add(t);
        });
         
        List<TicketData> ticketDataListSorted =ticketDataListNew.stream().sorted( Comparator.comparing(p -> p.getTicketCreatedDate())).collect(Collectors.toList());
        System.out.println("getChartDataTesla "+ticketDataListSorted.size());
        ticketDataListSorted.stream().forEach(t->System.out.println(t.toString()));
        
        return ticketDataListSorted;
		   	}

    @PostMapping("/chartData/meeza")
  	public List<com.bct.weeklystatus.model.TicketData> getChartDataMeeza()
  	{
      	System.out.println("getChartDataMeeza");

      	List<Long> projectIDList = new ArrayList<Long>();
      	projectIDList.add(500L);
          List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
          List<TicketData> ticketDataList = new ArrayList<TicketData>();
          projectStatusDetailList.forEach(projectStatusDetail->{
          	TicketData ticketData = new TicketData();
          	ticketData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
          	ticketData.setL1IssuesClosed(projectStatusDetail.getL1IssuesClosed());
          	ticketData.setL2IssuesClosed(projectStatusDetail.getL2IssuesClosed());
          	ticketData.setL1IssuesOpened(projectStatusDetail.getL1IssuesOpened());
          	ticketData.setL2IssuesOpened(projectStatusDetail.getL2IssuesOpened());
          	LocalDate day = projectStatusDetail.getDateSelection();
        
          	while (day.getDayOfWeek() != DayOfWeek.MONDAY)
          	 {
          		      day = day.minusDays(1);
          	 }
          	
          	ticketData.setTicketCreatedDate(day);
          	ticketDataList.add(ticketData);        	
          });
        
          ticketDataList.stream().forEach(t->System.out.println(t.toString()));
          Map<Optional<LocalDate>,TicketData> mapfTicketsByWeekly =
          	    ticketDataList
          	        .parallelStream()
          	        .collect(
          	            Collectors.groupingBy(e->Optional.ofNullable(e.getTicketCreatedDate()),                      
          	                  Collectors.reducing(
          	                		  new TicketData(LocalDate.MIN,0,0,0,0,""),
          	                		  (t1,t2)->
                  	                    new TicketData(t2.getTicketCreatedDate(),t1.getL1IssuesOpened()+t2.getL1IssuesOpened(),t1.getL1IssuesClosed()+t2.getL1IssuesClosed(),
                  	                    		t1.getL2IssuesOpened()+t2.getL2IssuesOpened(),t1.getL2IssuesClosed()+t2.getL2IssuesClosed(),t2.getProjectName()))));
          List<TicketData> ticketDataListNew = new ArrayList<TicketData>();
          
          mapfTicketsByWeekly.forEach((date,t)->{
          	ticketDataListNew.add(t);
          });
          List<TicketData> ticketDataListSorted =ticketDataListNew.stream().sorted( Comparator.comparing(p -> p.getTicketCreatedDate())).collect(Collectors.toList());
          System.out.println("getChartDataTesla "+ticketDataListSorted.size());
          ticketDataListSorted.stream().forEach(t->System.out.println(t.toString()));
          
          return ticketDataListSorted;
  		   	}

    
    @PostMapping("/chartData/cgtechimpl")
	public List<InfraData> getChartDataCGTechIMpl()
	{
    	System.out.println("getChartDataCGTechIMpl");

    	List<Long> projectIDList = new ArrayList<Long>();
    	projectIDList.add(700L);
    	List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
        List<InfraData> infraDataList = new ArrayList<InfraData>();
        projectStatusDetailList.forEach(projectStatusDetail->{
        
           InfraData infraData = new InfraData();
           infraData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
           infraData.setAppAvailablity(projectStatusDetail.getAppAvailablity());
           infraData.setAvailMemSpace(projectStatusDetail.getAvailMemSpace());
           infraData.setAvailCPULoad(projectStatusDetail.getCpuLoad());
           infraData.setAvailStorageSpace(projectStatusDetail.getAvailStorageSpace());;
           infraData.setNumberOfDb(projectStatusDetail.getNumOfDBs());
           infraData.setNumberOfServices(projectStatusDetail.getNumService());
           infraData.setServerName(projectStatusDetail.getServerName());
           
           LocalDate day = projectStatusDetail.getDateSelection();
           infraData.setWeekDuration(generateWeek(day));
           
          infraDataList.add(infraData);        	
        	
        });
           infraDataList.stream().forEach(t->System.out.println(t.toString()));
        Map<Optional<String>,Map<Optional<String>,InfraData>> mapfInfraByWeekly =
        		infraDataList
        	        .parallelStream()
        	        .collect(
        	            Collectors.groupingBy(e->Optional.ofNullable(e.getServerName()),
        	            		Collectors.groupingBy(e-> Optional.ofNullable(e.getWeekDuration()),
        	            		 Collectors.reducing(
        	                		  new InfraData("",0,0,0,0,0,0,"",""),
        	                		  (t1,t2)->
                	                    new InfraData(t2.getWeekDuration(),(t1.getAppAvailablity()+t2.getAppAvailablity()),(t1.getAvailMemSpace()+t2.getAvailMemSpace()),
                	                    		(t1.getAvailStorageSpace()+t2.getAvailStorageSpace()),(t1.getAvailCPULoad()+t2.getAvailCPULoad()),t1.getNumberOfDb()+t2.getNumberOfDb(),t1.getNumberOfServices()+t2.getNumberOfServices(),
                	                    		t2.getServerName(),t2.getProjectName())))));
        List<InfraData> ticketDataListNew = new ArrayList<InfraData>();
        mapfInfraByWeekly.forEach((servername,t)->{
        	t.forEach((key,data)->{
        		ticketDataListNew.add(data);
        	});
        	
        });
        ticketDataListNew.stream().forEach(t->System.out.println(t.toString()));
        System.out.println("getChartDataCGTechIMpl "+ticketDataListNew.size());

        return ticketDataListNew;
		   	}
 

    @PostMapping("/chartData/ivmsimpl")
	public List<IvmsInfraData> getChartDataIVMSIMpl()
	{
    	System.out.println("getChartDataIVMSIMpl");

    	List<Long> projectIDList = new ArrayList<Long>();
    	projectIDList.add(900L);
    	List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
        List<IvmsInfraData> infraDataList = new ArrayList<IvmsInfraData>();
        projectStatusDetailList.forEach(projectStatusDetail->{
           IvmsInfraData infraData = new IvmsInfraData();
           infraData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
           infraData.setAppAvailablity(projectStatusDetail.getAppAvailablity());
           infraData.setServerAvailablity(projectStatusDetail.getServerAvailablity());
           infraData.setDbAvailablity(projectStatusDetail.getDbAvailablity());
           infraData.setNumberOfClients(projectStatusDetail.getNumClients());
           infraData.setNumberOfDb(projectStatusDetail.getNumOfDBs());
           infraData.setNumberOfServers(projectStatusDetail.getNumOfServers());
           infraData.setNumberOfVehicles(projectStatusDetail.getNumVechicles());
           
           infraData.setVirtualFarms(projectStatusDetail.getVirtualFarms());

           LocalDate day = projectStatusDetail.getDateSelection();
           infraData.setWeekDuration(generateWeek(day));
           
          infraDataList.add(infraData);        	
        });
        infraDataList.stream().forEach(t->System.out.println(t.toString()));
        Map<Optional<String>,Map<Optional<String>,IvmsInfraData>> mapfInfraByWeekly =
        		infraDataList
        	        .stream()
        	        .collect(
        	            Collectors.groupingBy(e->Optional.ofNullable(e.getVirtualFarms()),
        	            		Collectors.groupingBy(e-> Optional.ofNullable(e.getWeekDuration()),
        	            		 Collectors.reducing(
        	                		  new IvmsInfraData("",100,100,100,0,0,0,0,"",""),
        	                		  (t1,t2)->
                	                    new IvmsInfraData(t2.getWeekDuration(),(t1.getAppAvailablity()+t2.getAppAvailablity())/2,(t1.getServerAvailablity()+t2.getServerAvailablity())/2,
                	                    		(t1.getDbAvailablity()+t2.getDbAvailablity())/2,
                	                    		t1.getNumberOfClients()+t2.getNumberOfClients(),
                	                    		t1.getNumberOfDb()+t2.getNumberOfDb(),
                	                    		t1.getNumberOfServers()+t2.getNumberOfServers(),
                	                    		t1.getNumberOfVehicles()+t2.getNumberOfVehicles(),
                	                    		t2.getVirtualFarms(),t2.getProjectName())))));
        List<IvmsInfraData> ticketDataListNew = new ArrayList<IvmsInfraData>();
        mapfInfraByWeekly.forEach((servername,t)->{
        	t.forEach((key,data)->{
        		ticketDataListNew.add(data);
        	});
        	
        });
        ticketDataListNew.stream().forEach(t->System.out.println(t.toString()));
        System.out.println("getChartDataIVMSIMpl "+ticketDataListNew.size());

        return ticketDataListNew;
		   	}
    @PostMapping("/chartData/teslanetworkop")
   	public List<com.bct.weeklystatus.model.TicketData> getChartDataTeslaNetworkOp()
   	{
       	System.out.println("getChartDataTeslaNetworkOp");

       	List<Long> projectIDList = new ArrayList<Long>();
       	projectIDList.add(1600L);
       	//projectIDList.add(500L);
           List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
           List<TicketData> ticketDataList = new ArrayList<TicketData>();
           projectStatusDetailList.forEach(projectStatusDetail->{
           	TicketData ticketData = new TicketData();
           	ticketData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
           	ticketData.setL1IssuesClosed(projectStatusDetail.getL1IssuesClosed());
           	ticketData.setL2IssuesClosed(projectStatusDetail.getL2IssuesClosed());
           	ticketData.setL1IssuesOpened(projectStatusDetail.getL1IssuesOpened());
           	ticketData.setL2IssuesOpened(projectStatusDetail.getL2IssuesOpened());
           	LocalDate day = projectStatusDetail.getDateSelection();
         
           	while (day.getDayOfWeek() != DayOfWeek.MONDAY)
           	 {
           		      day = day.minusDays(1);
           	 }
           	
           	ticketData.setTicketCreatedDate(day);
           	System.out.println(" before reducing "+ticketData.toString());
           	ticketDataList.add(ticketData);       
           	
           });
         
           ticketDataList.stream().forEach(t->System.out.println(t.toString()));
           Map<Optional<LocalDate>,TicketData> mapfTicketsByWeekly =
           	    ticketDataList
           	        .stream()
           	        .collect(
           	            Collectors.groupingBy(e->Optional.ofNullable(e.getTicketCreatedDate()),                      
           	                  Collectors.reducing(
           	                		  new TicketData(LocalDate.MIN,0,0,0,0,""),
           	                		  (t1,t2)->
                   	                    new TicketData(t2.getTicketCreatedDate(),t1.getL1IssuesOpened()+t2.getL1IssuesOpened(),t1.getL1IssuesClosed()+t2.getL1IssuesClosed(),
                   	                    		t1.getL2IssuesOpened()+t2.getL2IssuesOpened(),t1.getL2IssuesClosed()+t2.getL2IssuesClosed(),t2.getProjectName()))));
           List<TicketData> ticketDataListNew = new ArrayList<TicketData>();
           mapfTicketsByWeekly.forEach((date,t)->{
           	ticketDataListNew.add(t);
           });
            
           List<TicketData> ticketDataListSorted =ticketDataListNew.stream().sorted( Comparator.comparing(p -> p.getTicketCreatedDate())).collect(Collectors.toList());
           System.out.println("getChartDataOAB "+ticketDataListSorted.size());
           ticketDataListSorted.stream().forEach(t->System.out.println(t.toString()));
           
           return ticketDataListSorted;
   		   	}
    @PostMapping("/chartData/oabsupport")
   	public List<com.bct.weeklystatus.model.TicketData> getChartDataOAB()
   	{
       	System.out.println("getChartDataOAB");

       	List<Long> projectIDList = new ArrayList<Long>();
       	projectIDList.add(1100L);
       	//projectIDList.add(500L);
           List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
           List<TicketData> ticketDataList = new ArrayList<TicketData>();
           projectStatusDetailList.forEach(projectStatusDetail->{
           	TicketData ticketData = new TicketData();
           	ticketData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
           	ticketData.setL1IssuesClosed(projectStatusDetail.getL1IssuesClosed());
           	ticketData.setL2IssuesClosed(0);
           	ticketData.setL1IssuesOpened(projectStatusDetail.getL1IssuesOpened());
           	ticketData.setL2IssuesOpened(0);
           	LocalDate day = projectStatusDetail.getDateSelection();
         
           	while (day.getDayOfWeek() != DayOfWeek.MONDAY)
           	 {
           		      day = day.minusDays(1);
           	 }
           	
           	ticketData.setTicketCreatedDate(day);
           	System.out.println(" before reducing "+ticketData.toString());
           	ticketDataList.add(ticketData);       
           	
           });
         
           ticketDataList.stream().forEach(t->System.out.println(t.toString()));
           Map<Optional<LocalDate>,TicketData> mapfTicketsByWeekly =
           	    ticketDataList
           	        .stream()
           	        .collect(
           	            Collectors.groupingBy(e->Optional.ofNullable(e.getTicketCreatedDate()),                      
           	                  Collectors.reducing(
           	                		  new TicketData(LocalDate.MIN,0,0,0,0,""),
           	                		  (t1,t2)->
                   	                    new TicketData(t2.getTicketCreatedDate(),t1.getL1IssuesOpened()+t2.getL1IssuesOpened(),t1.getL1IssuesClosed()+t2.getL1IssuesClosed(),
                   	                    		t1.getL2IssuesOpened()+t2.getL2IssuesOpened(),t1.getL2IssuesClosed()+t2.getL2IssuesClosed(),t2.getProjectName()))));
           List<TicketData> ticketDataListNew = new ArrayList<TicketData>();
           mapfTicketsByWeekly.forEach((date,t)->{
           	ticketDataListNew.add(t);
           });
            
           List<TicketData> ticketDataListSorted =ticketDataListNew.stream().sorted( Comparator.comparing(p -> p.getTicketCreatedDate())).collect(Collectors.toList());
           System.out.println("getChartDataOAB "+ticketDataListSorted.size());
           ticketDataListSorted.stream().forEach(t->System.out.println(t.toString()));
           
           return ticketDataListSorted;
   		   	}

    @PostMapping("/chartData/uab")
   	public List<com.bct.weeklystatus.model.SRData> getChartDataUAB()
   	{
       	System.out.println("getChartDataUAB");

       	List<Long> projectIDList = new ArrayList<Long>();
       	projectIDList.add(1400L);
       	//projectIDList.add(500L);
           List<ProjectStatusDetail> projectStatusDetailList= projectDetailService.getAllProjectStatusDetails(projectIDList);
           List<SRData> srList = new ArrayList<SRData>();
           projectStatusDetailList.forEach(projectStatusDetail->{
        	   SRData srData = new SRData();
        	   srData.setProjectName(projectStatusDetail.getTaskDetil().getProjectDetail().getProjectName());
        	   srData.setSrCategory(projectStatusDetail.getTaskDetil().getTaskName());
        	   srData.setSrOpened(1);
        	   if(projectStatusDetail.getStatus()!=null && projectStatusDetail.getStatus().equalsIgnoreCase("completed"))
        		   {
        			   srData.setSrClosed(1);
        		   }
        	  LocalDate day = projectStatusDetail.getDateSelection();
         
           	while (day.getDayOfWeek() != DayOfWeek.MONDAY)
           	 {
           		      day = day.minusDays(1);
           	 }
           	
           	srData.setSrCreatedDate(day);
           	System.out.println(" before reducing "+srData.toString());
           	srList.add(srData);       
           	
           });
         
           srList.stream().forEach(t->System.out.println(t.toString()));
           Map<Optional<String>,Map<Optional<LocalDate>,SRData>> mapSrsByCategoryAndByWeekly =
           	    srList
           	        .stream()
           	        .collect(
           	        	 Collectors.groupingBy(e->Optional.ofNullable(e.getSrCategory()),
           	        		Collectors.groupingBy(e->Optional.ofNullable(e.getSrCreatedDate()),                      
           	                  Collectors.reducing(
           	                		  new SRData(LocalDate.MIN,"",0,0,""),
           	                		  (t1,t2)->
                   	                    new SRData(t2.getSrCreatedDate(),t2.getSrCategory(),t1.getSrOpened()+t2.getSrOpened(),t1.getSrClosed()+t2.getSrClosed(),
                   	                    		t2.getProjectName())))));
           List<SRData> srDataListNew = new ArrayList<SRData>();
           
          
           mapSrsByCategoryAndByWeekly.forEach((servername,t)->{
           	t.forEach((key,data)->{
           		srDataListNew.add(data);
           	});
           	
           });
           List<SRData> srDataListSorted =srDataListNew.stream().sorted( Comparator.comparing(p -> p.getSrCreatedDate())).collect(Collectors.toList());
           System.out.println("getChartDataUAB "+srDataListSorted.size());
           srDataListSorted.stream().forEach(t->System.out.println(t.toString()));
           
           return srDataListSorted;
   		   	}

   

    private String generateWeek(LocalDate day) {
        while (day.getDayOfWeek() != DayOfWeek.MONDAY)
    	 {
    		      day = day.minusDays(1);
    	 }
    	LocalDate firstDay = day;
    	while(day.getDayOfWeek()!= DayOfWeek.SUNDAY){
    		
    			day = day.plusDays(1);
    			
    			
  	}
    	LocalDate lastDay = day;
    	
     String firstDate = firstDay.getDayOfMonth()<10 ? "0"+firstDay.getDayOfMonth():String.valueOf(firstDay.getDayOfMonth());
     String lastDate = lastDay.getDayOfMonth()<10 ? "0"+lastDay.getDayOfMonth():String.valueOf(lastDay.getDayOfMonth());
     String week= firstDay.getMonth().getDisplayName(TextStyle.SHORT,Locale.ENGLISH)+" "+firstDate+","+firstDay.getYear()+
   		  "-" + lastDay.getMonth().getDisplayName(TextStyle.SHORT,Locale.ENGLISH)+" "+lastDate+","+lastDay.getYear();
    	
     System.out.println(week);
     return week;
    }
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}