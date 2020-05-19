package com.bct.weeklystatus.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.model.ProjectDetail;
import com.bct.weeklystatus.model.ProjectStatusDetail;
import com.bct.weeklystatus.model.TaskDetail;
import com.bct.weeklystatus.model.User;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.TaskDetailRepository;
import com.bct.weeklystatus.util.ClassConversion;

@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {

		
	@Autowired
    private ProjectDetailRepository projectDetailsRepository;

	@Autowired
    private TaskDetailRepository taskDetailRepository;


	
	@Override
	@Transactional
	public void saveProjectDetail(ProjectDetail projectDetail) {
		// TODO Auto-generated method stub
		
		projectDetailsRepository.insert(new ClassConversion().createProjectDetailEntity(projectDetail));
		
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
			/*
			 * ProjectStatusDetail projectStatusDetail = new ProjectStatusDetail();
			 * projectStatusDetail.setCreatedBy(projectStatusDetailEntity.getCreatedBy());
			 * projectStatusDetail.setDescription(projectStatusDetailEntity.getDescription()
			 * ); projectStatusDetail.setLastUpdatedBy(projectStatusDetailEntity.
			 * getLastUpdatedBy());
			 * projectStatusDetail.setCreationDate(projectStatusDetailEntity.getCreationDate
			 * ()); projectStatusDetail.setDateSelection(projectStatusDetailEntity.
			 * getDateSelection());
			 * projectStatusDetail.setDevelopmentCompletion(projectStatusDetailEntity.
			 * getDevelopmentCompletion());
			 * projectStatusDetail.setId(projectStatusDetailEntity.getId());
			 * projectStatusDetail.setL1IssuesClosed(projectStatusDetailEntity.
			 * getL1IssuesClosed());
			 * projectStatusDetail.setL1IssuesOpened(projectStatusDetailEntity.
			 * getL1IssuesOpened());
			 * projectStatusDetail.setL2IssuesOpened(projectStatusDetailEntity.
			 * getL2IssuesClosed());
			 * projectStatusDetail.setL2IssuesClosed(projectStatusDetailEntity.
			 * getL2IssuesOpened());
			 * projectStatusDetail.setLastUpdatedBy(projectStatusDetailEntity.
			 * getLastUpdatedBy());
			 * projectStatusDetail.setNumberBuildSprintCompleted(projectStatusDetailEntity.
			 * getNumberBuildSprintCompleted());
			 * projectStatusDetail.setNumBuildSprintWorkd(projectStatusDetailEntity.
			 * getNumBuildSprintWorkd());
			 */	
	    	listOfProjectStatusDetails.add(new ClassConversion().createProjectStatusDetailModel(projectStatusDetailEntity));
	    }
		return listOfProjectStatusDetails;
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
	    	
	    	listOfTaskDetails.add(new ClassConversion().createTaskDetailModel(taskDetailEntity));
	    }
		return listOfTaskDetails;
	}


	@Override
	public List<User> getAllUsers(Long projectID) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.User> listOfUserEntity = projectDetailsRepository.findAllUsers(projectID);
	    List<User> listOfUsers = new ArrayList<User>();
	    for(com.bct.weeklystatus.entities.User userEntity : listOfUserEntity) {
			/*
			 * User user = new User(); user.setCreatedBy(userEntity.getCreatedBy());
			 * user.setCreationDate(userEntity.getCreationDate());
			 * user.setLastUpdatedBy(userEntity.getLastUpdatedBy());
			 * user.setPassword(userEntity.getPassword());
			 * user.setUpdateDate(userEntity.getUpdateDate());
			 * user.setUserEmailAddress(userEntity.getUserEmailAddress());
			 * user.setUserID(userEntity.getUserID());
			 * user.setUserName(userEntity.getUserName());
			 * user.setUserRole(userEntity.getUserRole());
			 */
	    	
	    	listOfUsers.add(new ClassConversion().createUserModel(userEntity));
	    }
		return listOfUsers;
	}
	
	
}
