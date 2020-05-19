package com.bct.weeklystatus.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String password;
	private String emailId;
	private String[] userType;
	private ProjectDetail[] projectDetails;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private String createdBy;
	private String lastUpdatedBy;
	private boolean active=true;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
		System.out.println(" setPassword ");
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String[] getUserType() {
		return userType;
	}
	public void setUserType(String[] userType) {
		System.out.println(" setUserType ");
		this.userType = userType;
	}
	public ProjectDetail[] getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(ProjectDetail[] projectDetails) {
		this.projectDetails = projectDetails;
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
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", emailId=" + emailId
				+ ", userType=" + Arrays.toString(userType) + ", projectDetails=" + Arrays.toString(projectDetails)
				+ ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", createdBy=" + createdBy
				+ ", lastUpdatedBy=" + lastUpdatedBy + ", active=" + active + "]";
	}
	
		
}
