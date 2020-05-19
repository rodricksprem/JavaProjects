package com.bct.weeklystatus.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;

@Repository
@Transactional
public class ProjectStatusDetailsRepository 
{
	
	@Autowired
	private EntityManager entityManager;
	
	public ProjectStatusDetail findById(Integer id) {
		
		return entityManager.find(ProjectStatusDetail.class, id);
	}
	
	public void insert(ProjectStatusDetail projectStatusDetail) {
		
		entityManager.merge(projectStatusDetail);
		
	}
	
	public boolean delete(Integer id) {
		boolean flag = false;
		ProjectStatusDetail projectStatusDetail =findById(id);
		
		entityManager.remove(projectStatusDetail);
		System.out.println(" deleted projectstatus detail of "+id);
		flag=true;
		return flag;
	}
	
	public void insert(ProjectStatusDetail projectStatusDetail, TaskDetail taskDetail) {
		// TODO Auto-generated method stub
	
		projectStatusDetail.setTaskDetil(taskDetail);
		entityManager.flush();
		
		
			
	}
	
	public List<ProjectStatusDetail> findAllProjectStatus(){
		TypedQuery<ProjectStatusDetail>	 typedQuery=entityManager.createNamedQuery("find_all_status",ProjectStatusDetail.class);
		return typedQuery.getResultList();
		}
	
}
