package com.bct.weeklystatus.services;

import java.util.List;

import com.bct.weeklystatus.entities.TaskDetail;

public interface TaskDetailService {

	public TaskDetail saveTaskDetail(TaskDetail taskDetail,Long projectId);

	public Boolean deleteTaskDetail(Integer taskId);

	public void updateTaskDetail(TaskDetail taskDetail);

	public List<TaskDetail> findAllTasks(Long projectID);
	
}
