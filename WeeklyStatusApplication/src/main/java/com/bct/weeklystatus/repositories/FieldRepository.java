package com.bct.weeklystatus.repositories;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.Fields;
@Repository
@Transactional
public class FieldRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	
	public Fields findById(Long projectId) {
		System.out.println("findById "+projectId);
		return entityManager.find(Fields.class, projectId);
	}
		
	}
