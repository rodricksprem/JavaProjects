package com.bct.weeklystatus.services;

import java.util.List;

import com.bct.weeklystatus.model.ProjectDetail;
import com.bct.weeklystatus.model.User;

public interface UserService {

	public void saveUser(User user,Long projectId);

	public void deleteUser(User User);
	public User findByUsername(String username);
	public List<User> findAllUsers();
	public List<User> findAllUsers(Long projectId);
	public List<ProjectDetail> getAllProjectDetails(String userId);
}
