package com.bct.weeklystatus.repositories;

import java.util.List;

import com.bct.weeklystatus.entities.ProjectDetail;

public interface UserRepositoryCustom {

	List<ProjectDetail> getByUsername(String userID);

}
