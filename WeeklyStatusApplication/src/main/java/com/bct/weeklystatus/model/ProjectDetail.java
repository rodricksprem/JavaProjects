package com.bct.weeklystatus.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.bct.weeklystatus.entities.WeekStatus;


public class ProjectDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long projectId;
	private String projectName;
	private User[] users;
	private TaskDetail[] taskDetails;
	private ProjectStatusDetail[] projectStatusDetails;
	private Account account;
	private String projectOwner;
	private LocalDateTime projectStartDate;
	private LocalDateTime projectEndDate;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private String createdBy;
	private String lastUpdatedBy;
	private List<WeekStatus> weekDurationsList;

	public ProjectDetail() {
		
	}public Long getProjectId() {
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
	public User[] getUsers() {
		return users;
	}
	public void setUsers(User[] users) {
		this.users = users;
	}
	
	public TaskDetail[] getTaskDetails() {
		return taskDetails;
	}
	public void setTaskDetails(TaskDetail[] taskDetails) {
		this.taskDetails = taskDetails;
	}
	public ProjectStatusDetail[] getProjectStatusDetails() {
		return projectStatusDetails;
	}
	public void setProjectStatusDetails(ProjectStatusDetail[] projectStatusDetails) {
		this.projectStatusDetails = projectStatusDetails;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getProjectOwner() {
		return projectOwner;
	}
	public void setProjectOwner(String projectOwner) {
		this.projectOwner = projectOwner;
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
	public void setWeekDuration(List<WeekStatus> list) {
		this.weekDurationsList=list;
	}
	public List<WeekStatus> getWeekDuration() {
		return this.weekDurationsList;
	}
	@Override
	public String toString() {
		return "ProjectDetail [projectId=" + projectId + ", projectName=" + projectName + ", users="
				+ Arrays.toString(users) + ", taskDetails=" + Arrays.toString(taskDetails) + ", projectStatusDetails="
				+ Arrays.toString(projectStatusDetails) + ", account=" + account + ", projectOwner=" + projectOwner
				+", projectStartDate=" + projectStartDate + ", projectEndDate="
				+ projectEndDate + ", creationDate=" + creationDate + ", updateDate=" + updateDate + ", createdBy="
				+ createdBy + ", lastUpdatedBy=" + lastUpdatedBy + ", weekDuration=" + weekDurationsList + "]";
	}
	
	
}
