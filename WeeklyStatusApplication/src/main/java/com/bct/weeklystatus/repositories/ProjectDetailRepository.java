package com.bct.weeklystatus.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.Account;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.User;

import oracle.security.crypto.core.RSA;

import com.bct.weeklystatus.entities.ProjectStatusDetail;

@Repository
@Transactional
public class ProjectDetailRepository 
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	
	public ProjectDetail insert(ProjectDetail projectDetail) {
		
		return entityManager.merge(projectDetail);
		
	}
	
	public void delete(Long projectId) {
		ProjectDetail projectDetail = findById(projectId);

		entityManager.remove(projectDetail);
		
	}
	
	public void addTasksForProjects(Long projectId,String weekDuration, List<TaskDetail> taskDetails) {		
		ProjectDetail projectDetail = findById(projectId);
		logger.info("projectDetail.getTaskDetails() -> {}", projectDetail.getTaskDetails());
		for(TaskDetail task:taskDetails)
		{			
			projectDetail.getTaskDetails().add(task);
			task.setProjectDetail(projectDetail);
			entityManager.persist(task);
		}
		
	}
	
	
	
	public List<ProjectDetail> findAllProjectDetails(){
	
	TypedQuery<ProjectDetail>	 typedQuery=entityManager.createNamedQuery("find_all_projects",ProjectDetail.class);
	return typedQuery.getResultList();
	}
	public List<ProjectStatusDetail> findAllProjectStatus(Long projectId){
		List<ProjectStatusDetail> projectStatusDetails = new ArrayList<ProjectStatusDetail>();
		ProjectDetail projectDetail = findById(projectId);
		logger.info("findAllProjectStatus ");
		 projectDetail.getTaskDetails().stream().forEach(t->{
			
		projectStatusDetails.addAll(t.getProjectStatusDetails());
		});
		 return projectStatusDetails;
		
		
	}
	public List<ProjectStatusDetail> findAllProjectStatus(List<Long> projectIDList){
		logger.info("findAllProjectStatus ");
		List<ProjectStatusDetail> projectStatusDetailsList = new ArrayList<ProjectStatusDetail>();
		
		for(Long projectid:projectIDList) {
		List<ProjectStatusDetail> projectStatusDetailListNew = findAllProjectStatus(projectid);
		logger.info("findAllProjectStatus "+projectStatusDetailListNew.size());
		projectStatusDetailsList.addAll(projectStatusDetailListNew);
		}
		return projectStatusDetailsList;
		
		
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
	public List<ProjectDetail> findAllProjectStatus(){
		TypedQuery<ProjectDetail>	 typedQuery=entityManager.createNamedQuery("find_all_projects",ProjectDetail.class);
		return typedQuery.getResultList();
		}

	public List<ProjectDetail> findProjectDetil(Long projectId) {
		// TODO Auto-generated method stub
		List<ProjectDetail> projectDetailList = new ArrayList<ProjectDetail>();
		TypedQuery<ProjectDetail>	 typedQuery=entityManager.createQuery("select pd from ProjectDetail pd  where pd.projectId="+projectId,ProjectDetail.class);
		typedQuery.getResultList().forEach(rs->{
			projectDetailList.add(rs);
		});
		return projectDetailList;
	}
	public ProjectDetail findById(Long projectId) {
		// TODO Auto-generated method stub
		return entityManager.find(ProjectDetail.class, projectId);
	}

	public List<ProjectDetail> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
}
