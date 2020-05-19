package com.bct.weeklystatus.model;
import java.time.LocalDateTime;
import java.util.List;

public class TaskDetail {
	private Integer taskId;
	private String taskName;
	private ProjectDetail projectDetail;
	private List<ProjectStatusDetail> projectStatusDetails;
	private LocalDateTime creationDate;
	private LocalDateTime updateDate;
	private String createdBy;
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
