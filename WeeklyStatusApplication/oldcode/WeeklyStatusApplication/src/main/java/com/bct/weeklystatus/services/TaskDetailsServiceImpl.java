package com.bct.weeklystatus.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.bct.weeklystatus.model.TaskDetail;
import com.bct.weeklystatus.repositories.TaskDetailRepository;
import com.bct.weeklystatus.util.ClassConversion;

public class TaskDetailsServiceImpl implements TaskDetailService{

	@Autowired
	private TaskDetailRepository taskDetailRepository;
	@Override
	public void saveTaskDetail(TaskDetail taskDetail, Long projectID) {
		// TODO Auto-generated method stub
		taskDetailRepository.insert(new ClassConversion().createTaskDetailEntity(taskDetail));
		
	}

	@Override
	public void deleteTaskDetail(TaskDetail taskDetail) {
		// TODO Auto-generated method stub
		taskDetailRepository.delete(taskDetail.getTaskId());
	}

	
}
