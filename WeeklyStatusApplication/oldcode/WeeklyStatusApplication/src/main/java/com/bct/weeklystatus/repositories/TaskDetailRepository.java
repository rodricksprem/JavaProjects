package com.bct.weeklystatus.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.User;

@Repository
@Transactional
public class TaskDetailRepository {

	
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	public TaskDetail findById(Integer id) {
		return entityManager.find(TaskDetail.class, id);
	}
	
	public void insert(TaskDetail TaskDetail) {
		
		entityManager.merge(TaskDetail);
		
	}
	
	public void delete(Integer id) {
		TaskDetail TaskDetail =findById(id);
		entityManager.remove(TaskDetail);
		
	}
	public List<TaskDetail> findAllTaskDetails(){
		TypedQuery<TaskDetail>	 typedQuery=entityManager.createNamedQuery("find_all_tasks",TaskDetail.class);
		return typedQuery.getResultList();
		}
	
}
