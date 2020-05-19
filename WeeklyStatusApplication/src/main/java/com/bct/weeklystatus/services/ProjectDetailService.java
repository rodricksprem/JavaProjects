package com.bct.weeklystatus.services;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.User;
import java.util.List;

public interface ProjectDetailService {

	
	public ProjectDetail saveProjectDetail(ProjectDetail projectDetail);

	public void deleteProjectDetail(ProjectDetail projectDetail);
	
	public List<ProjectStatusDetail> getAllProjectStatusDetails(Long projectID);
	public List<ProjectStatusDetail> getAllProjectStatusDetails(List<Long> projectIDsList) ;
	public List<TaskDetail> getAllTasks(Long projectID);
	public List<User> getAllUsers(Long projectID);
	public List<ProjectDetail> getAllProjectDetails();

	public ProjectDetail getProjectDetail(Long projectId);

	public Boolean deleteWeekDuration(Long projectId, String weekDuration);
	

}
