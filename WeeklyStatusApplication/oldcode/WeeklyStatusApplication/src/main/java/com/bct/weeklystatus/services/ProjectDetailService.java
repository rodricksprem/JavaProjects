package com.bct.weeklystatus.services;

import com.bct.weeklystatus.model.ProjectDetail;
import com.bct.weeklystatus.model.ProjectStatusDetail;
import com.bct.weeklystatus.model.TaskDetail;
import com.bct.weeklystatus.model.User;
import java.util.List;

public interface ProjectDetailService {

	
	public void saveProjectDetail(ProjectDetail projectDetail);

	public void deleteProjectDetail(ProjectDetail projectDetail);
	
	public List<ProjectStatusDetail> getAllProjectStatusDetails(Long projectID);
	public List<TaskDetail> getAllTasks(Long projectID);
	public List<User> getAllUsers(Long projectID);
	

}
