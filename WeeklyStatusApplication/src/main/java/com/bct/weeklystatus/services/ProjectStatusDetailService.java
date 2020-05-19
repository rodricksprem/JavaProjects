package com.bct.weeklystatus.services;

import com.bct.weeklystatus.entities.ProjectStatusDetail;

public interface ProjectStatusDetailService {

	
	public void saveProjectStatusDetail(ProjectStatusDetail projectStatusDetail);


	boolean deleteProjectStatusDetail(Integer projectStatusId);


	public ProjectStatusDetail findById(Integer integer);
	

}
