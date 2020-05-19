package com.bct.weeklystatus.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.repositories.ProjectDetailRepository;
import com.bct.weeklystatus.repositories.ProjectStatusDetailsRepository;
import com.bct.weeklystatus.repositories.TaskDetailRepository;
@Service
@Transactional
public class TaskDetailsServiceImpl implements TaskDetailService{

	@Autowired
	private TaskDetailRepository taskDetailRepository;
	@Autowired
	private ProjectDetailRepository projectDetailRepository;
	@Autowired
	private ProjectStatusDetailsRepository projectStatusDetailRepository;
	@Override
	public TaskDetail saveTaskDetail(TaskDetail taskDetail,Long projectId) {
		
		// TODO Auto-generated method stub
		ProjectDetail projectDetail  = projectDetailRepository.findById(projectId);
		TaskDetail taskDetailNew = taskDetailRepository.insert(taskDetail);
		taskDetailNew.setProjectDetail(projectDetail);
		return taskDetailNew;
		
	}

	@Override
	public Boolean deleteTaskDetail(Integer taskId) {
		// TODO Auto-generated method stub
		
		return taskDetailRepository.delete(taskId);
	}

	@Override
	public void updateTaskDetail(TaskDetail taskDetail) {
		// TODO Auto-generated method stub
		//TaskDetail taskDetailEntity = taskDetailRepository.findById(taskDetail.getTaskId());
	//	taskDetailEntity.getProjectStatusDetails().forEach()
		taskDetailRepository.updateTaskDetail(taskDetail);
		
	}

	@Override
	public List<TaskDetail> findAllTasks(Long projectID) {
		// TODO Auto-generated method stub
		ProjectDetail projectDetail  = projectDetailRepository.findById(projectID);
		return projectDetail.getTaskDetails();
	}

	
}
