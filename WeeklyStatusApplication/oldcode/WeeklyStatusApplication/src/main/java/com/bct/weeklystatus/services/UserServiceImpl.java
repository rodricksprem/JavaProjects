package com.bct.weeklystatus.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.model.ProjectDetail;
import com.bct.weeklystatus.model.User;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.RoleRepository;
import com.bct.weeklystatus.repositories.UserRepoistory;
import com.bct.weeklystatus.util.ClassConversion;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private ProjectDetailRepository projectDetailRepository;
	@Autowired
	private UserRepoistory userRepoistory;
	 @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void saveUser(User user,Long projectID) {
		// TODO Auto-generated method stub
		com.bct.weeklystatus.entities.ProjectDetail projectDetailEntity = projectDetailRepository.findById(projectID);
		com.bct.weeklystatus.entities.User userEntity = new ClassConversion().createUserEntity(user);
		projectDetailEntity.addUser(userEntity);
		userEntity.addProjectDetail(projectDetailEntity);
		userEntity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepoistory.insert(userEntity);
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userRepoistory.delete(user.getUserID());
	}

	@Override
	public List<ProjectDetail> getAllProjectDetails(String userID) {
		// TODO Auto-generated method stub
		
		List<com.bct.weeklystatus.entities.ProjectDetail> listOfProjectDetailEntity = userRepoistory.findAllProjectDetails(userID);
	    List<ProjectDetail> listOfProjectDetails = new ArrayList<ProjectDetail>();
	    for(com.bct.weeklystatus.entities.ProjectDetail projectDetailEntity : listOfProjectDetailEntity) {
	    	
	    		listOfProjectDetails.add(new ClassConversion().createProjectDetailModel(projectDetailEntity));
	    }
		return listOfProjectDetails;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return new ClassConversion().createUserModel(userRepoistory.findByName(username));
	}

	@Override
	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.User> listOfUserEntity = userRepoistory.findAllUsers();
	    List<User> listOfUser = new ArrayList<User>();
	    for(com.bct.weeklystatus.entities.User userEntity : listOfUserEntity) {
	    	
	    	listOfUser.add(new ClassConversion().createUserModel(userEntity));
	    }
		return listOfUser;
	}

	@Override
	public List<User> findAllUsers(Long projectId) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.User> listOfUserEntity = projectDetailRepository.findAllUsers(projectId);
	    List<User> listOfUser = new ArrayList<User>();
	    for(com.bct.weeklystatus.entities.User userEntity : listOfUserEntity) {
	    	
	    	listOfUser.add(new ClassConversion().createUserModel(userEntity));
	    }
		return listOfUser;
	}

}
