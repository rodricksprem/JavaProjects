package com.bct.weeklystatus.services;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.User;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl  implements UserDetailsService {

	@Autowired
	private ProjectDetailRepository projectDetailRepository;
	@Autowired
	private UserRepository userRepository;
	 /*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    */
    
    
	public void saveUser(User userEntity) {
		// TODO Auto-generated method stub
		System.out.println("saveUser "+userEntity.getProjectDetails().size());
		List<ProjectDetail> myList = new CopyOnWriteArrayList<ProjectDetail>();
		
	     userEntity.getProjectDetails().retainAll(myList);
		
		for(ProjectDetail projectDetail : myList )
		{
			Long projectID = projectDetail.getProjectId();
		com.bct.weeklystatus.entities.ProjectDetail projectDetailEntity = projectDetailRepository.findById(projectID);
		if(projectDetailEntity!= null) {
			projectDetailEntity.getUsers().add(userEntity);	
		}
				
		}
		
		userRepository.save(userEntity);
		
	}

	public void deleteUser(String userId) {
		// TODO Auto-generated method stub
		userRepository.delete(userRepository.findByUsername(userId).get());
	}

	public List<ProjectDetail> getAllProjectDetails(String userID) {
		// TODO Auto-generated method stub
		
		List<ProjectDetail> listOfProjectDetailEntity = userRepository.getByUsername(userID);
	    
		return listOfProjectDetailEntity;
	}

	public Optional<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}
	public Optional<User> findByUserId(String userId) {
		// TODO Auto-generated method stub
		System.out.println("findByUserId(String userId) "+userId);
		return userRepository.findByUsername(userId);
	}

	public List<User> findAllUsers() {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.User> listOfUserEntity = userRepository.findAll();
	  
		return listOfUserEntity;
	}
	public List<User> findAllUsers(Long projectId) {
		// TODO Auto-generated method stub
		List<com.bct.weeklystatus.entities.User> listOfUserEntity = projectDetailRepository.findAllUsers(projectId);
	    
		return listOfUserEntity;
	}

	@Override
	  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
	    User user = userRepository.findByUsername(username).orElseThrow(
	        () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
	 
	    return UserPrinciple.build(user);
	  }
	
}
