package com.bct.weeklystatus.services;

import com.bct.weeklystatus.entities.Fields;

public interface FieldService {

	
	public Fields findByProjectId(Long projectId);
}
