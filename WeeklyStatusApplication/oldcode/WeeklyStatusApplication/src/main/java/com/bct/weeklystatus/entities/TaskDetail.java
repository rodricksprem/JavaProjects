package com.bct.weeklystatus.entities;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="BCT_TASK_DETAILS")
@NamedQuery(name="find_all_tasks",query = "select td from TaskDetail td")
public class TaskDetail {
	@Column(name="Task_ID")
	@Id
	
	private Integer taskId;
	@Column(name="Task_Name")
	private String taskName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "Project_ID", nullable = false)
	private ProjectDetail projectDetail;
	
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "taskDetil")
	private List<ProjectStatusDetail> projectStatusDetails;
	
	
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
	
		public TaskDetail() {
	
	}
		

	

		public TaskDetail(Integer taskId, String taskName, ProjectDetail projectDetail,
				List<ProjectStatusDetail> projectStatusDetails, LocalDateTime creationDate, LocalDateTime updateDate,
				String createdBy, String lastUpdatedBy) {
			super();
			this.taskId = taskId;
			this.taskName = taskName;
			this.projectDetail = projectDetail;
			this.projectStatusDetails = projectStatusDetails;
			this.creationDate = creationDate;
			this.updateDate = updateDate;
			this.createdBy = createdBy;
			this.lastUpdatedBy = lastUpdatedBy;
		}




		public Integer getTaskId() {
			return taskId;
		}

		public void setTaskId(Integer taskId) {
			this.taskId = taskId;
		}

		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public ProjectDetail getProjectDetail() {
			return projectDetail;
		}

		public void setProjectDetail(ProjectDetail projectDetail) {
			this.projectDetail = projectDetail;
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
