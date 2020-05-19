package com.bct.weeklystatus.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="BCT_PROJECT_DETAIL")

@NamedQuery(name="find_all_projects",query = "select pd from ProjectDetail pd")
public class ProjectDetail {
	@Column(name="Project_ID")
	@Id
	private Long projectId;
	
	@Column(name="Project_Name")
	private String projectName;
	
	@ManyToMany(mappedBy="projectDetails")
	private List<User> users;
	
	@OneToMany(cascade = CascadeType.ALL,
            mappedBy = "projectDetail")
	private List<TaskDetail> taskDetails;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,
            mappedBy = "projectDetail")
	private List<ProjectStatusDetail> projectStatusDetails;
	
	@Column(name="Account_Name")
	private String accountName;
	@Column(name="Project_Owner")
	private String projectOwner;
	@Column(name="project_status")
	private String projectStatus;
	@Column(name="project_start_date")
	private LocalDateTime projectStartDate;
	@Column(name="project_end_date")
	private LocalDateTime projectEndDate;
	@Column(name="CREATION_DATE")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name="LAST_UPDATE_DATE")
	@UpdateTimestamp
	private LocalDateTime updateDate;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="LAST_UPDATED_BY")
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
		this.getTaskDetails().add(taskDetail);
	}
	public void removeTaskDetail(TaskDetail taskDetail) {
		this.getTaskDetails().remove(taskDetail);
	}
	public List<ProjectStatusDetail> getProjectStatusDetails() {
		return projectStatusDetails;
	}
	public void setProjectStatusDetails(List<ProjectStatusDetail> projectStatusDetails) {
		this.projectStatusDetails = projectStatusDetails;
	}
	public void addProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		this.getProjectStatusDetails().add(projectStatusDetail);
	}
	public void removeProjectStatusDetail(ProjectStatusDetail projectStatusDetail) {
		this.getProjectStatusDetails().remove(projectStatusDetail);
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
