package com.bct.weeklystatus.model;

import java.time.LocalDateTime;
import java.util.List;

public class User {
	private String userID;
	private String userName;
	private List<ProjectDetail> projectDetails;
	private String password;
	private String passwordConfirm;
	private String userEmailAddress;
	private String userRole;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private String createdBy;
	private String lastUpdatedBy;
	public String getUserID() {
		return userID;
	}
	
	
	public User() {
	}


	public User(String userID, String userName, List<ProjectDetail> projectDetails, String password,
			String userEmailAddress, String userRole, LocalDateTime creationDate, LocalDateTime updateDate,
			String createdBy, String lastUpdatedBy) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.projectDetails = projectDetails;
		this.password = password;
		this.userEmailAddress = userEmailAddress;
		this.userRole = userRole;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<ProjectDetail> getProjectDetails() {
		return projectDetails;
	}


	public void setProjectDetails(List<ProjectDetail> projectDetails) {
		this.projectDetails = projectDetails;
	}

	public void addProjectDetail(ProjectDetail projectDetail) {
		this.projectDetails.add(projectDetail);
	}
	public void removeProjectDetail(ProjectDetail projectDetail) {
		this.projectDetails.remove(projectDetail);
	}
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserEmailAddress() {
		return userEmailAddress;
	}


	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}


	public LocalDateTime getUpdateDate() {
		return updateDate;
	}


	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}


	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	
}
