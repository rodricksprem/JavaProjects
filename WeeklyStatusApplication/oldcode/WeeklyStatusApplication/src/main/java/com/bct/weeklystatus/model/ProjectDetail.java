package com.bct.weeklystatus.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class ProjectDetail {
	private Long projectId;
	private String projectName;
	private List<User> users;
	private List<TaskDetail> taskDetails;
	private List<ProjectStatusDetail> projectStatusDetails;
	private String accountName;
	private String projectOwner;
	private String projectStatus;
	private LocalDateTime projectStartDate;
	private LocalDateTime projectEndDate;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private String createdBy;
	private String lastUpdatedBy;
	public ProjectDetail() {
		
	}
	public ProjectDetail(Long projectId, String projectName, List<User> users, List<TaskDetail> taskDetails,
			List<ProjectStatusDetail> projectStatusDetails, String accountName, String projectOwner,
			String projectStatus, LocalDateTime projectStartDate, LocalDateTime projectEndDate, LocalDateTime creationDate,
			LocalDateTime updateDate, String createdBy, String lastUpdatedBy) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.users = users;
		this.taskDetails = taskDetails;
		this.projectStatusDetails = projectStatusDetails;
		this.accountName = accountName;
		this.projectOwner = projectOwner;
		this.projectStatus = projectStatus;
		this.projectStartDate = projectStartDate;
		this.projectEndDate = projectEndDate;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void addUser(User user) {
		this.users.add(user);
	}
	public void removeUser(User user) {
		this.users.remove(user);
	}
	public List<TaskDetail> getTaskDetails() {
		return taskDetails;
	}
	public void setTaskDetails(List<TaskDetail> taskDetails) {
		this.taskDetails = taskDetails;
	}
	public void addTaskDetail(TaskDetail taskDetail) {
		this.taskDetails.add(taskDetail);
	}
	public void removeTaskDetail(TaskDetail taskDetail) {
		this.taskDetails.remove(taskDetail);
	}
	public List<ProjectStatusDetail> getProjectStatusDetails() {
		return projectStatusDetails;
	}
	public void setProjectStatusDetails(List<ProjectStatusDetail> projectStatusDetails) {
		this.projectStatusDetails = projectStatusDetails;
	}
	public void addProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		this.projectStatusDetails.add(projectStatusDetail);
	}
	public void removeProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		this.projectStatusDetails.remove(projectStatusDetail);
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public LocalDateTime getProjectStartDate() {
		return projectStartDate;
	}
	public void setProjectStartDate(LocalDateTime projectStartDate) {
		this.projectStartDate = projectStartDate;
	}
	public LocalDateTime getProjectEndDate() {
		return projectEndDate;
	}
	public void setProjectEndDate(LocalDateTime projectEndDate) {
		this.projectEndDate = projectEndDate;
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
	
	
	
	
	
	
}
