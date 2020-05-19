package com.bct.weeklystatus.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.model.ProjectStatusDetail;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.ProjectStatusDetailsRepository;
import com.bct.weeklystatus.repositories.TaskDetailRepository;
import com.bct.weeklystatus.util.ClassConversion;

@Service
public class ProjectStatusDetailServiceImpl implements ProjectStatusDetailService {

		
	@Autowired
    private ProjectDetailRepository projectDetailsRepository;

	@Autowired
    private TaskDetailRepository taskDetailRepository;

	
	@Autowired
    private ProjectStatusDetailsRepository projectStatusDetailsRepository;

	
	@Override
	@Transactional
	public void saveProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		// TODO Auto-generated method stub
		ProjectDetail projectDetail = projectDetailsRepository.findById(projectStatusDetail.getProjectID());
		TaskDetail taskDetail = taskDetailRepository.findById(projectStatusDetail.getTaskID());
		com.bct.weeklystatus.entities.ProjectStatusDetail projectStatusDetailEntity = new ClassConversion().createProjectStatusDetailEntity(projectStatusDetail);
		projectStatusDetailsRepository.insert(projectStatusDetailEntity,projectDetail,taskDetail);
		
	}
	
	
	/*
	 * private com.bct.weeklystatus.entities.ProjectStatusDetail
	 * createProjectStatusDetail(ProjectStatusDetail projectStatusDetail){
	 * 
	 * com.bct.weeklystatus.entities.ProjectStatusDetail projectStatusDetailEntity =
	 * new com.bct.weeklystatus.entities.ProjectStatusDetail();
	 * projectStatusDetailEntity.setDateSelection(projectStatusDetail.
	 * getDateSelection());
	 * projectStatusDetailEntity.setDevelopmentCompletion(projectStatusDetail.
	 * getDevelopmentCompletion());;
	 * projectStatusDetailEntity.setDescription(projectStatusDetail.getDescription()
	 * );; projectStatusDetailEntity.setL1IssuesClosed(projectStatusDetail.
	 * getL1IssuesClosed());
	 * projectStatusDetailEntity.setL1IssuesOpened(projectStatusDetail.
	 * getL1IssuesOpened());
	 * projectStatusDetailEntity.setL2IssuesClosed(projectStatusDetail.
	 * getL2IssuesClosed());
	 * projectStatusDetailEntity.setNumBuildSprintWorkd(projectStatusDetail.
	 * getNumberBuildSprintCompleted());
	 * projectStatusDetailEntity.setNumBuildSprintWorkd(projectStatusDetail.
	 * getNumBuildSprintWorkd());
	 * 
	 * return projectStatusDetailEntity; }
	 */

	@Override
	public void deleteProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		// TODO Auto-generated method stub
		projectStatusDetailsRepository.delete(projectStatusDetail.getId());;
		
		
	}
	
}
