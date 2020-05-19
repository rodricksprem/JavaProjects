package com.bct.weeklystatus.model;

import java.time.LocalDateTime;

public class ProjectStatusDetail {

	    private Long id;
		
		private Long projectID;
		private Integer taskID;
		private LocalDateTime dateSelection;
		private Integer l1IssuesOpened;
		private Integer l2IssuesOpened;
		private Integer l1IssuesClosed;
		private Integer l2IssuesClosed;
		private String description;
		private Integer developmentCompletion;
		private String remarks;
		private String numBuildSprintWorkd;
		private String numberBuildSprintCompleted;
		private String testsPerDay;
		private LocalDateTime creationDate;
		private LocalDateTime updateDate;
		private String createdBy;
		private String lastUpdatedBy;
		public ProjectStatusDetail() {
		
		}
		public ProjectStatusDetail(Long id, Long projectID, Integer taskID, LocalDateTime dateSelection,
				Integer l1IssuesOpened, Integer l2IssuesOpened, Integer l1IssuesClosed, Integer l2IssuesClosed,
				String description, Integer developmentCompletion, String remarks, String numBuildSprintWorkd,
				String numberBuildSprintCompleted, String testsPerDay, LocalDateTime creationDate,
				LocalDateTime updateDate, String createdBy, String lastUpdatedBy) {
			super();
			this.id = id;
			this.projectID = projectID;
			this.taskID = taskID;
			this.dateSelection = dateSelection;
			this.l1IssuesOpened = l1IssuesOpened;
			this.l2IssuesOpened = l2IssuesOpened;
			this.l1IssuesClosed = l1IssuesClosed;
			this.l2IssuesClosed = l2IssuesClosed;
			this.description = description;
			this.developmentCompletion = developmentCompletion;
			this.remarks = remarks;
			this.numBuildSprintWorkd = numBuildSprintWorkd;
			this.numberBuildSprintCompleted = numberBuildSprintCompleted;
			this.testsPerDay = testsPerDay;
			this.creationDate = creationDate;
			this.updateDate = updateDate;
			this.createdBy = createdBy;
			this.lastUpdatedBy = lastUpdatedBy;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getProjectID() {
			return projectID;
		}
		public void setProjectID(Long projectID) {
			this.projectID = projectID;
		}
		public Integer getTaskID() {
			return taskID;
		}
		public void setTaskID(Integer taskID) {
			this.taskID = taskID;
		}
		public LocalDateTime getDateSelection() {
			return dateSelection;
		}
		public void setDateSelection(LocalDateTime dateSelection) {
			this.dateSelection = dateSelection;
		}
		public Integer getL1IssuesOpened() {
			return l1IssuesOpened;
		}
		public void setL1IssuesOpened(Integer l1IssuesOpened) {
			this.l1IssuesOpened = l1IssuesOpened;
		}
		public Integer getL2IssuesOpened() {
			return l2IssuesOpened;
		}
		public void setL2IssuesOpened(Integer l2IssuesOpened) {
			this.l2IssuesOpened = l2IssuesOpened;
		}
		public Integer getL1IssuesClosed() {
			return l1IssuesClosed;
		}
		public void setL1IssuesClosed(Integer l1IssuesClosed) {
			this.l1IssuesClosed = l1IssuesClosed;
		}
		public Integer getL2IssuesClosed() {
			return l2IssuesClosed;
		}
		public void setL2IssuesClosed(Integer l2IssuesClosed) {
			this.l2IssuesClosed = l2IssuesClosed;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Integer getDevelopmentCompletion() {
			return developmentCompletion;
		}
		public void setDevelopmentCompletion(Integer developmentCompletion) {
			this.developmentCompletion = developmentCompletion;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getNumBuildSprintWorkd() {
			return numBuildSprintWorkd;
		}
		public void setNumBuildSprintWorkd(String numBuildSprintWorkd) {
			this.numBuildSprintWorkd = numBuildSprintWorkd;
		}
		public String getNumberBuildSprintCompleted() {
			return numberBuildSprintCompleted;
		}
		public void setNumberBuildSprintCompleted(String numberBuildSprintCompleted) {
			this.numberBuildSprintCompleted = numberBuildSprintCompleted;
		}
		public String getTestsPerDay() {
			return testsPerDay;
		}
		public void setTestsPerDay(String testsPerDay) {
			this.testsPerDay = testsPerDay;
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
