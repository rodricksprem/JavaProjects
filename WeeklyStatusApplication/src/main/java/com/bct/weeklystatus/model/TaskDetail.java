package com.bct.weeklystatus.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TaskDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer taskId;
	private String taskName;
	private String taskType;
	private ProjectDetail projectDetail;
	private ProjectStatusDetail[] projectStatusDetails;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private String createdBy;
	private String lastUpdatedBy;
	
		public TaskDetail() {
	
	}
		

	

		public TaskDetail(Integer taskId, String taskName, ProjectDetail projectDetail,
				ProjectStatusDetail[] projectStatusDetails, LocalDateTime creationDate, LocalDateTime updateDate,
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

		public ProjectStatusDetail[] getProjectStatusDetails() {
			return projectStatusDetails;
		}

		public void setProjectStatusDetails(ProjectStatusDetail[] projectStatusDetails) {
			this.projectStatusDetails = projectStatusDetails;
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




		@Override
		public String toString() {
			return "TaskDetail [taskId=" + taskId + ", taskName=" + taskName + ", taskType=" + taskType
					+ ", projectDetail=" + projectDetail + ", projectStatusDetails="
					+ Arrays.toString(projectStatusDetails) + ", creationDate=" + creationDate + ", updateDate="
					+ updateDate + ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy + "]";
		}




		public String getTaskType() {
			return taskType;
		}




		public void setTaskType(String taskType) {
			this.taskType = taskType;
		}

		
	}
