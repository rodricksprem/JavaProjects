package com.bct.weeklystatus.services;

import com.bct.weeklystatus.model.TaskDetail;

public interface TaskDetailService {

	public void saveTaskDetail(TaskDetail taskDetail,Long projectID);

	public void deleteTaskDetail(TaskDetail taskDetail);
	
}
