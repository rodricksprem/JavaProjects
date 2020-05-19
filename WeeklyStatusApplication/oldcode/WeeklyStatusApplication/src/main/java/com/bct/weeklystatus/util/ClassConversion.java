package com.bct.weeklystatus.util;

import com.bct.weeklystatus.model.ProjectDetail;
import com.bct.weeklystatus.model.ProjectStatusDetail;
import com.bct.weeklystatus.model.TaskDetail;
import com.bct.weeklystatus.model.User;

public class ClassConversion {

	
public com.bct.weeklystatus.entities.ProjectStatusDetail createProjectStatusDetailEntity(ProjectStatusDetail projectStatusDetail){
		
		com.bct.weeklystatus.entities.ProjectStatusDetail projectStatusDetailEntity = new com.bct.weeklystatus.entities.ProjectStatusDetail();
		projectStatusDetailEntity.setDateSelection(projectStatusDetail.getDateSelection());
		projectStatusDetailEntity.setDevelopmentCompletion(projectStatusDetail.getDevelopmentCompletion());;
		projectStatusDetailEntity.setDescription(projectStatusDetail.getDescription());;
		projectStatusDetailEntity.setL1IssuesClosed(projectStatusDetail.getL1IssuesClosed());
		projectStatusDetailEntity.setL1IssuesOpened(projectStatusDetail.getL1IssuesOpened());
		projectStatusDetailEntity.setL2IssuesClosed(projectStatusDetail.getL2IssuesClosed());
		projectStatusDetailEntity.setNumBuildSprintWorkd(projectStatusDetail.getNumberBuildSprintCompleted());
		projectStatusDetailEntity.setNumBuildSprintWorkd(projectStatusDetail.getNumBuildSprintWorkd());
	
		return projectStatusDetailEntity;
	}

public com.bct.weeklystatus.model.ProjectStatusDetail createProjectStatusDetailModel(com.bct.weeklystatus.entities.ProjectStatusDetail projectStatusDetailEntity)
	{
		ProjectStatusDetail projectStatusDetail = new ProjectStatusDetail();
    	projectStatusDetail.setCreatedBy(projectStatusDetailEntity.getCreatedBy());
    	projectStatusDetail.setDescription(projectStatusDetailEntity.getDescription());
    	projectStatusDetail.setLastUpdatedBy(projectStatusDetailEntity.getLastUpdatedBy());
    	projectStatusDetail.setCreationDate(projectStatusDetailEntity.getCreationDate());
    	projectStatusDetail.setDateSelection(projectStatusDetailEntity.getDateSelection());
    	projectStatusDetail.setDevelopmentCompletion(projectStatusDetailEntity.getDevelopmentCompletion());
    	projectStatusDetail.setId(projectStatusDetailEntity.getId());
    	projectStatusDetail.setL1IssuesClosed(projectStatusDetailEntity.getL1IssuesClosed());
    	projectStatusDetail.setL1IssuesOpened(projectStatusDetailEntity.getL1IssuesOpened());
    	projectStatusDetail.setL2IssuesOpened(projectStatusDetailEntity.getL2IssuesClosed());
    	projectStatusDetail.setL2IssuesClosed(projectStatusDetailEntity.getL2IssuesOpened());
    	projectStatusDetail.setLastUpdatedBy(projectStatusDetailEntity.getLastUpdatedBy());
    	projectStatusDetail.setNumberBuildSprintCompleted(projectStatusDetailEntity.getNumberBuildSprintCompleted());
    	projectStatusDetail.setNumBuildSprintWorkd(projectStatusDetailEntity.getNumBuildSprintWorkd());
    	return projectStatusDetail;
	}
public com.bct.weeklystatus.entities.ProjectDetail createProjectDetailEntity(ProjectDetail projectDetail){
	
	com.bct.weeklystatus.entities.ProjectDetail projectDetailEntity = new com.bct.weeklystatus.entities.ProjectDetail();
	projectDetailEntity.setAccountName(projectDetail.getAccountName());
	projectDetailEntity.setProjectId(projectDetail.getProjectId());
	projectDetailEntity.setProjectEndDate(projectDetail.getProjectEndDate());
	projectDetailEntity.setProjectName(projectDetail.getProjectName());
	projectDetailEntity.setProjectOwner(projectDetail.getProjectOwner());
	projectDetailEntity.setProjectStartDate(projectDetail.getProjectStartDate());
	if(projectDetail.getProjectEndDate().isAfter(projectDetail.getProjectStartDate())) {
		projectDetailEntity.setProjectStatus("ACTIVE");
	}else {
		projectDetailEntity.setProjectStatus("INACTIVE");
	}
	return projectDetailEntity;
}

public ProjectDetail createProjectDetailModel(com.bct.weeklystatus.entities.ProjectDetail projectDetailEntity){
	
	ProjectDetail projectDetail = new ProjectDetail();
	projectDetail.setAccountName(projectDetailEntity.getAccountName());
	projectDetail.setProjectId(projectDetailEntity.getProjectId());
	projectDetail.setProjectEndDate(projectDetailEntity.getProjectEndDate());
	projectDetail.setProjectName(projectDetailEntity.getProjectName());
	projectDetail.setProjectOwner(projectDetailEntity.getProjectOwner());
	projectDetail.setProjectStartDate(projectDetailEntity.getProjectStartDate());
	projectDetail.setProjectStatus(projectDetailEntity.getProjectStatus());
	return projectDetail;
}

public com.bct.weeklystatus.model.User createUserModel(com.bct.weeklystatus.entities.User userEntity){
	
	User user  = new User();
	user.setCreatedBy(userEntity.getCreatedBy());
	user.setCreationDate(userEntity.getCreationDate());
	user.setLastUpdatedBy(userEntity.getLastUpdatedBy());
	user.setPassword(userEntity.getPassword());
	user.setUpdateDate(userEntity.getUpdateDate());
	user.setUserEmailAddress(userEntity.getUserEmailAddress());
	user.setUserID(userEntity.getUserID());
	user.setUserName(userEntity.getUserName());
	user.setUserRole(userEntity.getUserRole());
	return user;
	}	

public com.bct.weeklystatus.entities.User createUserEntity(User user){
	
	com.bct.weeklystatus.entities.User userEntity  = new com.bct.weeklystatus.entities.User();
	userEntity.setCreatedBy(user.getCreatedBy());
	userEntity.setCreationDate(user.getCreationDate());
	userEntity.setLastUpdatedBy(user.getLastUpdatedBy());
	userEntity.setPassword(user.getPassword());
	userEntity.setUpdateDate(user.getUpdateDate());
	userEntity.setUserEmailAddress(user.getUserEmailAddress());
	userEntity.setUserID(user.getUserID());
	userEntity.setUserName(user.getUserName());
	userEntity.setUserRole(user.getUserRole());
	return userEntity;
	}	



public com.bct.weeklystatus.model.TaskDetail createTaskDetailModel(com.bct.weeklystatus.entities.TaskDetail taskDetailEntity){
	TaskDetail taskDetail = new TaskDetail();
	taskDetail.setCreatedBy(taskDetailEntity.getCreatedBy());
	taskDetail.setCreationDate(taskDetailEntity.getCreationDate());
	taskDetail.setLastUpdatedBy(taskDetailEntity.getLastUpdatedBy());
	taskDetail.setTaskId(taskDetailEntity.getTaskId());
	taskDetail.setTaskName(taskDetailEntity.getTaskName());
	taskDetail.setUpdateDate(taskDetailEntity.getUpdateDate());
	return taskDetail;
}

public com.bct.weeklystatus.entities.TaskDetail createTaskDetailEntity(TaskDetail taskDetail){
	com.bct.weeklystatus.entities.TaskDetail taskDetailEntity = new com.bct.weeklystatus.entities.TaskDetail();
	taskDetailEntity.setCreatedBy(taskDetail.getCreatedBy());
	taskDetailEntity.setCreationDate(taskDetail.getCreationDate());
	taskDetailEntity.setLastUpdatedBy(taskDetail.getLastUpdatedBy());
	taskDetailEntity.setTaskId(taskDetail.getTaskId());
	taskDetailEntity.setTaskName(taskDetail.getTaskName());
	taskDetailEntity.setUpdateDate(taskDetail.getUpdateDate());
	return taskDetailEntity;
}

}
