package com.bct.weeklystatus.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BCT_DASHBOARD_FIELDS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Fields {
	@Id
	@Column(name="Project_Id")
	private Long projectId;
	@Column(name="idFlag")
	private Boolean idFlag=false;
	@Column(name="dateSelectionFlag")
	private Boolean dateSelectionFlag=false;
	@Column(name="l1IssuesOpenedFlag")
	private Boolean l1IssuesOpenedFlag=false;
	@Column(name="l2IssuesOpenedFlag")
	private Boolean l2IssuesOpenedFlag=false;
	@Column(name="l1IssuesClosedFlag")
	private Boolean l1IssuesClosedFlag=false;
	@Column(name="l2IssuesClosedFlag")
	private Boolean l2IssuesClosedFlag=false;
	@Column(name="descriptionFlag")
	private Boolean descriptionFlag=false;
	@Column(name="developmentCompletionFlag")
	private Boolean developmentCompletionFlag=false;
	@Column(name="remarksFlag")
	private Boolean remarksFlag=false;
	@Column(name="numBuildSprintWorkdFlag")
	private Boolean numBuildSprintWorkdFlag=false;
	@Column(name="numberBuildSprintCompletedFlag")
	private Boolean numberBuildSprintCompletedFlag=false;
	@Column(name="testsPerDayFlag")
	private Boolean testsPerDayFlag=false;
	@Column(name="createdByFlag")
	private Boolean createdByFlag=false;
	@Column(name="lastUpdatedByFlag")
	private Boolean lastUpdatedByFlag=false;
	@Column(name="serverNameFlag")
	private Boolean serverNameFlag=false;
	@Column(name="requestedByFlag")
	private Boolean requestedByFlag=false;
	@Column(name="approvedByFlag")
	private Boolean approvedByFlag=false;
	@Column(name="issueNumberFlag")
	private Boolean issueNumberFlag=false;
	@Column(name="customerNameFlag")
	private Boolean customerNameFlag=false;
	@Column(name="moduleFlag")
	private Boolean moduleFlag=false;
	@Column(name="resolutionFlag")
	private Boolean resolutionFlag=false;
	@Column(name="targetDateFlag")
	private Boolean targetDateFlag=false;
	@Column(name="virtualFarmsFlag")
	private Boolean virtualFarmsFlag=false;
	@Column(name="numOfServersFlag")
	private Boolean numOfServersFlag=false;
	@Column(name="numOfDBsFlag")
	private Boolean numOfDBsFlag=false;
	@Column(name="serverAvailablityFlag")
	private Boolean serverAvailablityFlag=false;
	@Column(name="cpuLoadFlag")
	private Boolean cpuLoadFlag=false;
	@Column(name="availMemSpaceFlag")
	private Boolean availMemSpaceFlag=false;
	@Column(name="availStorageSpaceFlag")
	private Boolean availStorageSpaceFlag=false;
	@Column(name="dbAvailablityFlag")
	private Boolean dbAvailablityFlag=false;
	@Column(name="appAvailablityFlag")
	private Boolean appAvailablityFlag=false;
	@Column(name="numClientsFlag")
	private Boolean numClientsFlag=false;
	@Column(name="numServiceFlag")
	private Boolean numServiceFlag=false;
	@Column(name="numVechiclesFlag")
	private Boolean numVechiclesFlag=false;
	@Column(name="locationFlag")
	private Boolean locationFlag=false;
	@Column(name="statusFlag")
	private Boolean statusFlag=false;
	@Column(name="resourceFlag")
	private Boolean resourceFlag=false;
	@Column(name="taskTypeFlag")
	private Boolean taskTypeFlag=false;
	@Column(name="activityFlag")
	private Boolean activityFlag=false;
	@Column(name="accountFlag")
	private Boolean accountFlag=false;
	@Column(name="subProjectFlag")
	private Boolean subProjectFlag=false;
	@Column(name="subProjectTypeFlag")
	private Boolean subProjectTypeFlag=false;
	@Column(name="memberFlag")
	private Boolean memberFlag=false;
	@Column(name="developmentFlag")
	private Boolean developmentFlag=false;
												
}
