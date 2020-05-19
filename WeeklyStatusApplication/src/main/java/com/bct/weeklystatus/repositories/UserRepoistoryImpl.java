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
public class UserRepoistoryImpl implements UserRepositoryCustom{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	
	public User findByUserName(String userId) {
		return entityManager.find(User.class, userId);
	}
	@Override
	public List<ProjectDetail> getByUsername(String userID){
		
		User user = findByUserName(userID);
	
		logger.info("findAllProjectDetails ");
		return user.getProjectDetails();
		
		
	}
	
	
}
