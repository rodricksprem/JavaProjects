package com.bct.weeklystatus.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.Size;

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
	
	public TaskDetail insert(TaskDetail taskDetail) {
		
		TaskDetail taskDetailNew =entityManager.merge(taskDetail);
		return taskDetailNew;
	}
	
	public Boolean delete(Integer id) {
		TaskDetail taskDetail =findById(id);
		if(taskDetail==null) {
			return false;
		}
		entityManager.remove(taskDetail);
		return true;
	}
	public List<TaskDetail> findAllTaskDetails(){
		TypedQuery<TaskDetail>	 typedQuery=entityManager.createNamedQuery("find_all_tasks",TaskDetail.class);
		return typedQuery.getResultList();
		}

	public void update(TaskDetail taskDetail) {
		// TODO Auto-generated method stub
		entityManager.merge(taskDetail);
	}

	public void updateTaskDetail(TaskDetail taskDetail) { 
	
		TaskDetail taskDetailNew  = findById(taskDetail.getTaskId());
		System.out.println(taskDetail.getProjectStatusDetails().size());
		taskDetailNew.setProjectStatusDetails(taskDetail.getProjectStatusDetails());
		 entityManager.merge(taskDetailNew);

		// TODO Auto-generated method stub
		
	}
	
}
