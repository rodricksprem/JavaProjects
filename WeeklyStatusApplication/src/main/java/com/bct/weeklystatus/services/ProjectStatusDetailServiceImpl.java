package com.bct.weeklystatus.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.ProjectStatusDetailsRepository;
import com.bct.weeklystatus.repositories.TaskDetailRepository;

@Service
public class ProjectStatusDetailServiceImpl implements ProjectStatusDetailService {

		
	@Autowired
    private ProjectStatusDetailsRepository projectStatusDetailsRepository;

	
	@Override
	@Transactional
	public void saveProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		// TODO Auto-generated method stub
		
		TaskDetail taskDetail = projectStatusDetail.getTaskDetil();
		projectStatusDetailsRepository.insert(projectStatusDetail,taskDetail);
		
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
	public boolean deleteProjectStatusDetail(Integer projectStatusId) {
		// TODO Auto-generated method stub
	
		return projectStatusDetailsRepository.delete(projectStatusId);
		
		
	}


	@Override
	public ProjectStatusDetail findById(Integer id) {
		// TODO Auto-generated method stub
		return projectStatusDetailsRepository.findById(id);
	}
	
}
