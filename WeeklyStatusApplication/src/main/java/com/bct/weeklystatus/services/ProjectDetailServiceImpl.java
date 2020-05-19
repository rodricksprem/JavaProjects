package com.bct.weeklystatus.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.entities.Account;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.User;
import com.bct.weeklystatus.repositories.AccountRepositoryImpl;
import com.bct.weeklystatus.repositories.AccountRepostiry;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.TaskDetailRepository;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {


	@Autowired
    private AccountRepositoryImpl accountRepository;

	@Autowired
    private ProjectDetailRepository projectDetailsRepository;

	@Autowired
    private TaskDetailRepository taskDetailRepository;


	
	@Override
	@Transactional
	public ProjectDetail saveProjectDetail(ProjectDetail projectDetail) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findAccount(projectDetail.getProjectId());
		projectDetail.setAccount(account);
		return projectDetailsRepository.insert(projectDetail);
		
	}
	
	
	

	@Override
	public void deleteProjectDetail(ProjectDetail projectDetail) {
		// TODO Auto-generated method stub
		projectDetailsRepository.delete(projectDetail.getProjectId());;
		
		
	}


	@Override
	public List<ProjectStatusDetail> getAllProjectStatusDetails(Long projectID) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.ProjectStatusDetail> listOfProjectStatusDetailsEntity = projectDetailsRepository.findAllProjectStatus(projectID);
	    List<ProjectStatusDetail> listOfProjectStatusDetails = new ArrayList<ProjectStatusDetail>();
	    for(com.bct.weeklystatus.entities.ProjectStatusDetail projectStatusDetailEntity: listOfProjectStatusDetailsEntity) {
			listOfProjectStatusDetails.add(projectStatusDetailEntity);
	    }
		return listOfProjectStatusDetailsEntity;
	}

	public List<ProjectStatusDetail> getAllProjectStatusDetails(List<Long> projectIDsList) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.ProjectStatusDetail> listOfProjectStatusDetailsEntity = projectDetailsRepository.findAllProjectStatus(projectIDsList);
		return listOfProjectStatusDetailsEntity;
	    
	}

	@Override
	public List<TaskDetail> getAllTasks(Long projectID) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.TaskDetail> listOfTaskDetailsEntity = projectDetailsRepository.findAllTaskDetails(projectID);
	    List<TaskDetail> listOfTaskDetails = new ArrayList<TaskDetail>();
	    for(com.bct.weeklystatus.entities.TaskDetail taskDetailEntity:listOfTaskDetailsEntity) {
			/*
			 * TaskDetail taskDetail = new TaskDetail();
			 * taskDetail.setCreatedBy(taskDetailEntity.getCreatedBy());
			 * taskDetail.setCreationDate(taskDetailEntity.getCreationDate());
			 * taskDetail.setLastUpdatedBy(taskDetailEntity.getLastUpdatedBy());
			 * taskDetail.setTaskId(taskDetailEntity.getTaskId());
			 * taskDetail.setTaskName(taskDetailEntity.getTaskName());
			 * taskDetail.setUpdateDate(taskDetailEntity.getUpdateDate());
			 */
	    	
	    	listOfTaskDetails.add(taskDetailEntity);
	    }
		return listOfTaskDetails;
	}


	@Override
	public List<User> getAllUsers(Long projectID) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.User> listOfUserEntity = projectDetailsRepository.findAllUsers(projectID);
	    
		return listOfUserEntity;
	}
	
	public List<ProjectDetail> getAllProjectDetails() {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.ProjectDetail> listOfProjectDetailEntity = projectDetailsRepository.findAllProjectDetails();
	    
		return listOfProjectDetailEntity;
	}




	@Override
	public ProjectDetail getProjectDetail(Long projectId) {
		// TODO Auto-generated method stub
		return projectDetailsRepository.findById(projectId);
		
	}




	@Override
	public Boolean deleteWeekDuration(Long projectId, String weekDuration) {
		// TODO Auto-generated method stub
		
		ProjectDetail projectDetail = projectDetailsRepository.findById(projectId);
		System.out.println(" projectDetail.getWeekDuration().size() "+projectDetail.getWeekDuration().size());
		
		projectDetail.setWeekDuration(projectDetail.getWeekDuration().stream().filter(wd -> {
			System.out.println(" filter ..."+wd.getWeekduration());
		return !(wd.getWeekduration().equalsIgnoreCase(weekDuration));}).collect(Collectors.toList()));
		System.out.println(" projectDetail.getWeekDuration().size() "+projectDetail.getWeekDuration().size());
		
		projectDetailsRepository.insert(projectDetail);
		return true;
	}



}
