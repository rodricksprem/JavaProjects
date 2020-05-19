package com.bct.weeklystatus.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ProjectStatusDetail implements Serializable{

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		private Integer id;
		
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
		private Integer numService;
		private Integer numVechicles;
		private Integer numClients;
		private Integer appAvailablity;
		private Integer dbAvailablity;
		private Integer availMemSpace;
		private Integer cpuLoad;
		private Integer serverAvailablity;
		private Integer numOfDBs;
		private Integer numOfServers;
		private String virtualFarms;
		private String targetDate;
		private String resolution;
		private String module;
		private String customerName;
		private String issueNumber;
		private String approvedBy;
		private String serverName;
		private String requestedBy;
		private String taskName;

		private String taskType;

		private String location;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
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
		public void setTaskName(String taskName) {
			// TODO Auto-generated method stub
			this.taskName=taskName;
			
		}
		public String getTaskName() {
			return this.taskName;
		}
		public void setTaskType(String taskType) {
			// TODO Auto-generated method stub
			this.taskType=taskType;
		}
		public String getTaskType() {
			return this.taskType;
		}

		
		public String getServerName() {
			return serverName;
		}

		public void setServerName(String serverName) {
			this.serverName = serverName;
		}

		public String getRequestedBy() {
			return this.requestedBy;
		}

		public void setRequestedBy(String requestedBy) {
			this.requestedBy = requestedBy;
		}

		public String getApprovedBy() {
			return approvedBy;
		}

		public void setApprovedBy(String approvedBy) {
			this.approvedBy = approvedBy;
		}

		public String getIssueNumber() {
			return issueNumber;
		}

		public void setIssueNumber(String issueNumber) {
			this.issueNumber = issueNumber;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		public String getModule() {
			return module;
		}

		public void setModule(String module) {
			this.module = module;
		}

		public String getResolution() {
			return resolution;
		}

		public void setResolution(String resolution) {
			this.resolution = resolution;
		}

		public String getTargetDate() {
			return targetDate;
		}

		public void setTargetDate(String targetDate) {
			this.targetDate = targetDate;
		}

		public String getVirtualFarms() {
			return virtualFarms;
		}

		public void setVirtualFarms(String virtualFarms) {
			this.virtualFarms = virtualFarms;
		}

		public Integer getNumOfServers() {
			return numOfServers;
		}

		public void setNumOfServers(Integer numOfServers) {
			this.numOfServers = numOfServers;
		}

		public Integer getNumOfDBs() {
			return numOfDBs;
		}

		public void setNumOfDBs(Integer numOfDBs) {
			this.numOfDBs = numOfDBs;
		}

		public Integer getServerAvailablity() {
			return serverAvailablity;
		}

		public void setServerAvailablity(Integer serverAvailablity) {
			this.serverAvailablity = serverAvailablity;
		}

		public Integer getCpuLoad() {
			return cpuLoad;
		}

		public void setCpuLoad(Integer cpuLoad) {
			this.cpuLoad = cpuLoad;
		}

		public Integer getAvailMemSpace() {
			return availMemSpace;
		}

		public void setAvailMemSpace(Integer availMemSpace) {
			this.availMemSpace = availMemSpace;
		}

		public Integer getAvailStorageSpace() {
			return appAvailablity;
		}

		public void setAvailStorageSpace(Integer availStorageSpace) {
			this.appAvailablity = availStorageSpace;
		}

		public Integer getDbAvailablity() {
			return dbAvailablity;
		}

		public void setDbAvailablity(Integer dbAvailablity) {
			this.dbAvailablity = dbAvailablity;
		}

		public Integer getAppAvailablity() {
			return appAvailablity;
		}

		public void setAppAvailablity(Integer appAvailablity) {
			this.appAvailablity = appAvailablity;
		}

		public Integer getNumClients() {
			return numClients;
		}

		public void setNumClients(Integer numClients) {
			this.numClients = numClients;
		}

		public Integer getNumService() {
			return numService;
		}

		public void setNumService(Integer numService) {
			this.numService = numService;
		}

		public Integer getNumVechicles() {
			return numVechicles;
		}

		public void setNumVechicles(Integer numVechicles) {
			this.numVechicles = numVechicles;
		}
		
		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}
		@Override
		public String toString() {
			return "ProjectStatusDetail [id=" + id + ", projectID=" + projectID + ", taskID=" + taskID
					+ ", dateSelection=" + dateSelection + ", l1IssuesOpened=" + l1IssuesOpened + ", l2IssuesOpened="
					+ l2IssuesOpened + ", l1IssuesClosed=" + l1IssuesClosed + ", l2IssuesClosed=" + l2IssuesClosed
					+ ", description=" + description + ", developmentCompletion=" + developmentCompletion + ", remarks="
					+ remarks + ", numBuildSprintWorkd=" + numBuildSprintWorkd + ", numberBuildSprintCompleted="
					+ numberBuildSprintCompleted + ", testsPerDay=" + testsPerDay + ", creationDate=" + creationDate
					+ ", updateDate=" + updateDate + ", createdBy=" + createdBy + ", lastUpdatedBy=" + lastUpdatedBy
					+ ", numService=" + numService + ", numVechicles=" + numVechicles + ", numClients=" + numClients
					+ ", appAvailablity=" + appAvailablity + ", dbAvailablity=" + dbAvailablity + ", availMemSpace="
					+ availMemSpace + ", cpuLoad=" + cpuLoad + ", serverAvailablity=" + serverAvailablity
					+ ", numOfDBs=" + numOfDBs + ", numOfServers=" + numOfServers + ", virtualFarms=" + virtualFarms
					+ ", targetDate=" + targetDate + ", resolution=" + resolution + ", module=" + module
					+ ", customerName=" + customerName + ", issueNumber=" + issueNumber + ", approvedBy=" + approvedBy
					+ ", serverName=" + serverName + ", requestedBy=" + requestedBy + ", taskName=" + taskName
					+ ", taskType=" + taskType + ", location=" + location + "]";
		}

}
