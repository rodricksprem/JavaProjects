package com.bct.weeklystatus.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.entities.Fields;
import com.bct.weeklystatus.repositories.FieldRepository;

@Service
@Transactional
public class FieldServiceImpl implements FieldService {

	@Autowired
	private FieldRepository fieldRepository;
	@Override
	public Fields findByProjectId(Long projectId) {
		// TODO Auto-generated method stub
		return fieldRepository.findById(projectId);
	}

}
