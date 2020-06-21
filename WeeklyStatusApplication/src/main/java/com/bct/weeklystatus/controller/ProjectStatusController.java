package com.bct.weeklystatus.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.WeekStatus;
import com.bct.weeklystatus.services.AccountService;
import com.bct.weeklystatus.services.ProjectDetailService;
import com.bct.weeklystatus.services.ProjectStatusDetailService;
import com.bct.weeklystatus.services.TaskDetailService;

@RestController
@RequestMapping("/bctprojectstatus")
//@CrossOrigin(origins = {"http://localhost:4200","http://10.240.8.62:7001","http://localhost:8088"}, allowedHeaders="*")

public class ProjectStatusController {
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ProjectDetailService projectDetailService;
    
    @Autowired
    private TaskDetailService taskDetailService;
    
//Angular
    @GetMapping("/getWeeks/{projectId}")
    public HashSet<String> getWeeks(@PathVariable Long projectId )
	{
    	System.out.println("getWeeks for projectId "+projectId);

    	HashSet<String> weeks = new HashSet<String>();

    	ProjectDetail projectDetail= projectDetailService.getProjectDetail(projectId);
        
        projectDetail.getWeekDuration().stream().forEach(wd -> {
        	weeks.add(wd.getWeekduration());
        });;
        return weeks;
	}

     @GetMapping("/statusdetail/{projectID}")
	public List<TaskDetail> getProjectDetails(@PathVariable Long projectID)
	{
    	System.out.println("getProjectDetails for projectId");
    	
    	
    	List<TaskDetail> taskDetailList= taskDetailService.findAllTasks(projectID);
    	
    	List<TaskDetail> taskDetailSortedList = taskDetailList.stream().map(td->sortByDate(td)).collect(Collectors.toList());
    	
    	System.out.println("return ..."+taskDetailSortedList.size());
 	return taskDetailList;

	}
 

    @GetMapping(path="/allprojects",produces = "application/json")
   	public List<ProjectDetail> getProjectDetails()
   	{
       	System.out.println("getProjectDetails ...");
    	List<ProjectDetail> projectDetailsList = projectDetailService.getAllProjectDetails();
    	System.out.println("getProjectDetails ..."+projectDetailsList.size());
    	
    	Comparator<ProjectDetail> nameComparator = (lhs, rhs) -> lhs.getProjectName().compareTo(rhs.getProjectName());
         
    	   Comparator<WeekStatus> dateCompartor = (lhs, rhs) -> {
    			try {
    				return getStartDate(lhs.getWeekduration()).compareTo(getStartDate(rhs.getWeekduration()));
    			} catch (ParseException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				return 0;
    			}
    		};
    	       
    	      return  projectDetailsList.stream().sorted(Comparator.nullsLast(nameComparator)).map(pd->{
    	    	  pd.setWeekDuration(pd.getWeekDuration().stream().sorted(dateCompartor).
    	    			  collect(Collectors.toList()));
    	    	  pd.getTaskDetails().stream().map(taskDetail->sortByDate(taskDetail));
    	    	  return pd;
    	    	  }).collect(Collectors.toList());
    	      
    			
    		}
            private TaskDetail sortByDate(TaskDetail taskDetail) {
            	
            	Comparator<ProjectStatusDetail> statusdateCompartor = (lhs, rhs) -> {
        			try {
        				return lhs.getDateSelection().compareTo(rhs.getDateSelection());
        			}catch (NullPointerException e) { // when dateselection is null
        				return 0;
        			}
        		};
            	taskDetail.setProjectStatusDetails(taskDetail.getProjectStatusDetails().stream().
            			sorted(statusdateCompartor).collect(Collectors.toList()));
            	
            	return taskDetail;
            }
    	    static Date getStartDate(String weekDuration) throws ParseException {
    	    String startDateInStr=	weekDuration.split("-")[0];
    	    DateFormat format = new java.text.SimpleDateFormat("MMM dd,yyyy", Locale.ENGLISH);
    	    Date date = format.parse(startDateInStr);
    		return date;
    	    			
    	    }
    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}