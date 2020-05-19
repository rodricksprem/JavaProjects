package com.bct.weeklystatus.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.User;
import com.bct.weeklystatus.entities.ProjectStatusDetail;


@Repository
@Transactional
public class ProjectDetailRepository 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	public ProjectDetail findById(Long id) {
		return entityManager.find(ProjectDetail.class, id);
	}
	
	public void insert(ProjectDetail projectDetail) {
		
		entityManager.merge(projectDetail);
		
	}
	
	public void delete(Long id) {
		ProjectDetail projectDetail =findById(id);
		entityManager.remove(projectDetail);
		
	}
	
	public void addTasksForProjects(Long projectID, List<TaskDetail> taskDetails) {		
		ProjectDetail projectDetail = findById(projectID);
		logger.info("projectDetail.getTaskDetails() -> {}", projectDetail.getTaskDetails());
		for(TaskDetail task:taskDetails)
		{			
			projectDetail.addTaskDetail(task);
			task.setProjectDetail(projectDetail);
			entityManager.persist(task);
		}
	}
	
	public void addProjectStatusForProjects(Long projectID, List<ProjectStatusDetail> projectStatusDetails) {		
		ProjectDetail projectDetail = findById(projectID);
		logger.info("projectDetail.getProjectStatusDetails() -> {}", projectDetail.getProjectStatusDetails());
		for(ProjectStatusDetail projectStatusDetail:projectStatusDetails)
		{			
			projectDetail.addProjectStatusDetail(projectStatusDetail);
			projectStatusDetail.setProjectDetail(projectDetail);
			entityManager.persist(projectDetail);
		}
	}
	
	public List<ProjectDetail> findAllProjectDetails(){
	TypedQuery<ProjectDetail>	 typedQuery=entityManager.createNamedQuery("find_all_projects",ProjectDetail.class);
	return typedQuery.getResultList();
	}
	public List<ProjectStatusDetail> findAllProjectStatus(Long projectID){
	
		ProjectDetail projectDetail = findById(projectID);
		logger.info("findAllProjectStatus ");
		return projectDetail.getProjectStatusDetails();
		
		
	}
	

	public List<TaskDetail> findAllTaskDetails(Long projectID){
	
		ProjectDetail projectDetail = findById(projectID);
		logger.info("findAllTaskDetails ");
		return projectDetail.getTaskDetails();
		
		
	}
	
	
	public List<User> findAllUsers(Long projectID){
		
		ProjectDetail projectDetail = findById(projectID);
		logger.info("findAllUsers ");
		return projectDetail.getUsers();
		
		
	}
	
}
