package com.bct.weeklystatus.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.User;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
@Repository
@Transactional
public class UserRepoistory {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	public User findById(String userId) {
		return entityManager.find(User.class, userId);
	}
	public User findByName(String username) {
		return entityManager.find(User.class, username);
	}
	public void insert(User User) {
		
		entityManager.merge(User);
		
	}
	
	public void delete(String userID) {
		User user =findById(userID);
		entityManager.remove(user);
		
	}
	
	public void addUserForProject(String userID, List<ProjectDetail> projectDetails) {		
		User user = findById(userID);
		logger.info("addUserForProject");
		for(ProjectDetail projectDetail:projectDetails) {
			projectDetail.addUser(user);
			user.addProjectDetail(projectDetail);
		}
		
	}
	
	public List<ProjectDetail> findAllProjectDetails(String userID){
		
		User user = findById(userID);
	
		logger.info("findAllProjectDetails ");
		return user.getProjectDetails();
		
		
	}
	
	public List<User> findAllUsers(){
		TypedQuery<User>	 typedQuery=entityManager.createNamedQuery("find_all_users",User.class);
		return typedQuery.getResultList();
		}
	
}
