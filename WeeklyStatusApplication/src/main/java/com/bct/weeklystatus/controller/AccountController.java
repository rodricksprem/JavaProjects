package com.bct.weeklystatus.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bct.weeklystatus.entities.Account;
import com.bct.weeklystatus.entities.Fields;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.WeekStatus;
import com.bct.weeklystatus.services.AccountService;
import com.bct.weeklystatus.services.FieldService;
import com.bct.weeklystatus.services.ProjectDetailService;
import com.bct.weeklystatus.services.ProjectStatusDetailService;
import com.bct.weeklystatus.services.TaskDetailService;

@RestController
@RequestMapping("/bctaccount")
//@CrossOrigin(origins = {"http://localhost:4200","http://10.240.8.62:7001","http://localhost:8088"}, allowedHeaders="*")

public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private ProjectDetailService projectDetailService;

    @Autowired
    private TaskDetailService taskDetailService;
    
    @Autowired
    private FieldService fieldService;

    @Autowired
    private ProjectStatusDetailService projectStatusDetailService;
    
//Angular
    @PostMapping("/accountList")
	public List<com.bct.weeklystatus.model.Account> getAccountNames()
	{
    	System.out.println("getAccountNames");

        List<Account> accounts= accountService.findAllAccounts();
        List<com.bct.weeklystatus.model.Account> accountList = new ArrayList<com.bct.weeklystatus.model.Account>();
     
        accounts.forEach(account ->{
     	      com.bct.weeklystatus.model.Account accounttemp = new com.bct.weeklystatus.model.Account();
         	   accounttemp.setAccountID(account.getAccountID());
         	   accounttemp.setAccountName(account.getAccountName());
         	   accountList.add(accounttemp);

     	   
     	          });
        System.out.println("return ...");
 	return accountList;
		
	}
    @PostMapping("/accountDetails")
	public List<com.bct.weeklystatus.model.Account> getAccountDetails()
	{
    	System.out.println("getAccountDetails");
    	
    	
       List<Account> accounts= accountService.findAllAccounts();
       List<com.bct.weeklystatus.model.Account> accountList = new ArrayList<com.bct.weeklystatus.model.Account>();
    
       accounts.forEach(account ->{
    	   account.getProjectDetails().forEach(projecttemp -> {
    	      projecttemp.getWeekDuration().forEach( week->{
    		   com.bct.weeklystatus.model.Account accounttemp = new com.bct.weeklystatus.model.Account();
        	   accounttemp.setAccountID(account.getAccountID());
        	   accounttemp.setAccountOwner(account.getAccountOwner());
        	   accounttemp.setAccountStatus(account.getAccountStatus());
        	   accounttemp.setProjectID(projecttemp.getProjectId());
        	   accounttemp.setProjectName(projecttemp.getProjectName());
        	   accounttemp.setProjectStatus(week.getProjectStatus());
        	   accounttemp.setProjectType(projecttemp.getProjectType());
        	   accounttemp.setWeekDuration(week.getWeekduration());
        	   accounttemp.setRemarks(account.getRemarks());
        	   System.out.println(account.getAccountName());
        	   accounttemp.setAccountName(account.getAccountName());
        	   accountList.add(accounttemp);
    		});

    	   });
    	          });
       System.out.println("return ...");
       Comparator<com.bct.weeklystatus.model.Account> nameComparator = (lhs, rhs) -> lhs.getAccountName().compareTo(rhs.getAccountName());
       Comparator<com.bct.weeklystatus.model.Account> dateCompartor = (lhs, rhs) -> {
		try {
			return getStartDate(lhs.getWeekDuration()).compareTo(getStartDate(rhs.getWeekDuration()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	};
       
      return  accountList.stream().sorted(Comparator.nullsLast(nameComparator).thenComparing(dateCompartor)).collect(Collectors.toList());
		
	}
    static Date getStartDate(String weekDuration) throws ParseException {
    String startDateInStr=	weekDuration.split("-")[0];
    DateFormat format = new java.text.SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
    Date date = format.parse(startDateInStr);
	return date;
    			
    }
    @GetMapping("/name/{accountId}")
	public List<com.bct.weeklystatus.model.ProjectDetail> getProjectDetails(@PathVariable String accountId)
	{
    	System.out.println("getProjectDetails "+accountId);
    	
    	List<ProjectDetail> projectDetailsList= accountService.getAllProjectDetails(accountId);

    	List<com.bct.weeklystatus.model.ProjectDetail> projectDetailsListNew = new ArrayList<com.bct.weeklystatus.model.ProjectDetail>();
        
    	projectDetailsList.forEach(projectDetail ->{
     	      com.bct.weeklystatus.model.ProjectDetail projectDetailTemp = new com.bct.weeklystatus.model.ProjectDetail();
     	  //    projectDetailTemp.getWeekDuration().forEach(wd->{
     	      projectDetailTemp.setProjectId(projectDetail.getProjectId());
     	      projectDetailTemp.setProjectName(projectDetail.getProjectName());
     	      
         	  projectDetailTemp.setWeekDuration(projectDetail.getWeekDuration());
     	      projectDetailsListNew.add(projectDetailTemp);

     	    //  });
     	          });
     	      
        System.out.println("return ...");
 	return projectDetailsListNew;

	}
    @PostMapping("/taskDetail/delete/{taskId}")
	public Boolean removeTaskDetail(@PathVariable Integer taskId)
	{
    	System.out.println("removeTaskDetail "+taskId);
    	
    	Boolean isDelete = taskDetailService.deleteTaskDetail(taskId);
     	return isDelete;

	}
    @PostMapping("/weekDuration/delete/{projectId}/{weekDuration}")
   	public Boolean removeWeekDuration(@PathVariable Long projectId,@PathVariable String weekDuration)
   	{
       	System.out.println("removeWeekDuration "+weekDuration);
       	
       	Boolean isDelete = projectDetailService.deleteWeekDuration(projectId,weekDuration);
        	return isDelete;

   	}

    @PostMapping("/seachproject/weekly")
	public List<com.bct.weeklystatus.model.Account> getProjectDetails(@RequestBody ProjectDetail projectDetail)
	{
    	System.out.println("getProjectDetailsWeekly "+projectDetail.toString());
    	WeekStatus weekDurationInstance = projectDetail.getWeekDuration().get(0);
    	ProjectDetail foundProjectDetail= accountService.getProjectDetails(projectDetail.getAccount().getAccountID(),projectDetail.getProjectId(),weekDurationInstance.getWeekduration());
    	WeekStatus weekStatus = foundProjectDetail.getWeekDuration().stream().filter(wd -> wd.getWeekduration().equalsIgnoreCase(weekDurationInstance.getWeekduration())).findFirst().get();
    	List<com.bct.weeklystatus.model.Account> accounts  = new ArrayList<com.bct.weeklystatus.model.Account>();
    	com.bct.weeklystatus.model.Account accounttemp = new com.bct.weeklystatus.model.Account();
    	
    	accounttemp.setAccountID(foundProjectDetail.getAccount().getAccountID());
 	   accounttemp.setAccountOwner(foundProjectDetail.getAccount().getAccountOwner());
 	   accounttemp.setAccountStatus(foundProjectDetail.getAccount().getAccountStatus());
 	   accounttemp.setProjectID(foundProjectDetail.getProjectId());
 	   accounttemp.setProjectName(foundProjectDetail.getProjectName());
 	   accounttemp.setProjectStatus(weekStatus.getProjectStatus());
 	   accounttemp.setProjectType(foundProjectDetail.getProjectType());
 	   accounttemp.setWeekDuration(weekStatus.getWeekduration());
 	   accounttemp.setRemarks(foundProjectDetail.getAccount().getRemarks());
 	   accounttemp.setAccountName(foundProjectDetail.getAccount().getAccountName());
 	   accounts.add(accounttemp);
    	
    	
    	return accounts;

	}

    @GetMapping("/weekduration")
   	public HashSet<String> getWeekDuration()
   	{
       	System.out.println("weekduration");
       	
       	List<ProjectDetail> projectDetailsList= accountService.getAllProjectDetails();

       	HashSet<String> weekDurationList = new HashSet<String>();
           
       	projectDetailsList.forEach(projectDetail ->{
       		projectDetail.getWeekDuration().forEach(wd-> {
       				weekDurationList.add(wd.getWeekduration());
       		});
        	   
        	          });
           System.out.println("return ...");
          
    	return  weekDurationList;
   	}


    @PostMapping("/projectStatusDetail/update")
   	public Boolean updateProjectStatusDetail(@RequestBody TaskDetail taskDetail)
   	{
    	
       	System.out.println("updateProjectStatusDetail ");
       	taskDetailService.updateTaskDetail(taskDetail);
      	System.out.println("return ...");
          
    	return true;
   	}

    @PostMapping("/projectStatusDetail/delete")
   	public Boolean removeProjectStatusDetail(@RequestBody List<Integer> inputDataList)
   	{
    	TaskDetail taskDetail=null;
    	if(inputDataList.size()>0) {
    		ProjectStatusDetail projectStatusDetail = projectStatusDetailService.findById(inputDataList.get(0));
    		taskDetail=projectStatusDetail.getTaskDetil();
    	}
    	
       	System.out.println("removeProjectStatusDetail ");
       	List<ProjectStatusDetail> projectStatusListNew =taskDetail.getProjectStatusDetails().stream().filter(pd ->{
       		return inputDataList.indexOf(pd.getId())<0;
       	}).collect(Collectors.toList());
       	System.out.println("removeProjectStatusDetail "+projectStatusListNew.size());
       	
       	taskDetail.setProjectStatusDetails(projectStatusListNew);
       	taskDetailService.updateTaskDetail(taskDetail);
       	inputDataList.forEach(statusId->{
       			projectStatusDetailService.deleteProjectStatusDetail(statusId);
       	});
      	System.out.println("return ...");
      	return true;
   	}

    @PostMapping("/taskDetail/create")
   	public TaskDetail createTaskDetail(@RequestBody ProjectDetail projectDetail)
   	{
       	System.out.println("createTaskDetail");
       	TaskDetail taskDetail = projectDetail.getTaskDetails().get(0);
       	TaskDetail taskDetailNew= taskDetailService.saveTaskDetail(taskDetail,projectDetail.getProjectId());
           System.out.println("return ..."+taskDetailNew.getTaskId());
          taskDetail.setTaskId(taskDetailNew.getTaskId());
        return taskDetail;
   	}
    @PostMapping("/projectDetail/create")
   	public ProjectDetail createProjectDetail(@RequestBody ProjectDetail projectDetail)
   	{
       	System.out.println("createProjectDetail");
      
       	projectDetailService.saveProjectDetail(projectDetail);
          
    	return projectDetail;
   	}
    @PostMapping("/projectDetail/update")
   	public ProjectDetail updateProjectDetail(@RequestBody ProjectDetail projectDetail)
   	{
       	System.out.println("updateProjectDetail "+projectDetail.getProjectId() );
      
       	projectDetailService.saveProjectDetail(projectDetail);
          
    	return projectDetail;
   	}
    @PostMapping(path="/fields/{projectId}",produces = "application/json")
   	public Fields getProjectFields(@PathVariable Long projectId)
   	{
       	System.out.println("getProjectFields "+projectId);
       	
       
    	Fields fields =  fieldService.findByProjectId(projectId);
    	System.out.println(fields.toString());
    	return fields;

   	}
    
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}