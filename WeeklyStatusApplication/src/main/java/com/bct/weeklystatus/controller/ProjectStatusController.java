package com.bct.weeklystatus.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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

       @PostMapping("/accountDetails")
	public List<com.bct.weeklystatus.model.Account> getAccountDetails()
	{
    	System.out.println("getAccountDetails");
    	
    	
       List<Account> accounts= accountService.findAllAccounts();
       List<com.bct.weeklystatus.model.Account> accountList = new ArrayList<com.bct.weeklystatus.model.Account>();
    
       accounts.forEach(account ->{
    	   account.getProjectDetails().forEach(projecttemp -> {
    		   projecttemp.getWeekDuration().forEach( wd -> {
    		   com.bct.weeklystatus.model.Account accounttemp = new com.bct.weeklystatus.model.Account();
        	   accounttemp.setAccountID(account.getAccountID());
        	   accounttemp.setAccountOwner(account.getAccountOwner());
        	   accounttemp.setAccountStatus(account.getAccountStatus());
        	   accounttemp.setProjectID(projecttemp.getProjectId());
        	   accounttemp.setProjectName(projecttemp.getProjectName());

        	   accounttemp.setWeekDuration(wd.getWeekduration());
        	   accounttemp.setProjectStatus(wd.getProjectStatus());
        	   accounttemp.setProjectType(projecttemp.getProjectType());
        	   accounttemp.setRemarks(account.getRemarks());
        	   System.out.println(account.getAccountName());
        	   accounttemp.setAccountName(account.getAccountName());
        	   accountList.add(accounttemp);
    		   });

    	   });
    	          });
       System.out.println("return ...");
	return accountList;
		
	}

    @GetMapping("/statusdetail/{projectID}")
	public List<TaskDetail> getProjectDetails(@PathVariable Long projectID)
	{
    	System.out.println("getProjectDetails for projectId");
    	
    	
    	List<TaskDetail> taskDetailList= taskDetailService.findAllTasks(projectID);
    	
    	
    	
    	System.out.println("return ..."+taskDetailList.size());
 	return taskDetailList;

	}
 

    @GetMapping(path="/allprojects",produces = "application/json")
   	public List<ProjectDetail> getProjectDetails()
   	{
       	System.out.println("getProjectDetails ...");
    	List<ProjectDetail> projectDetailsList = projectDetailService.getAllProjectDetails();
    	System.out.println("getProjectDetails ..."+projectDetailsList.size());
    	return projectDetailsList;
  	   	      
       	}
   	

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }
}