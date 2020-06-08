package com.bct.weeklystatus.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BCT_PROJECT_STATUS_DETAILS")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "lastUpdatedBy"},
        allowGetters = true
)

@NamedQuery(name="find_all_status",query = "select ps from ProjectStatusDetail ps")
public class ProjectStatusDetail {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "projectstatus_Sequence")
    @SequenceGenerator(name = "projectstatus_Sequence", sequenceName = "PROJECTSTATUS_SEQ")
    private Integer id;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name="projectId")
	 * 
	 * @JsonBackReference(value="projectdetail_projectstatusdetails") private
	 * ProjectDetail projectDetail;
	 */
	
	@ManyToOne
	@JoinColumn(name="task_id")
	@JsonBackReference(value="taskdetail_projectstatusdetails")
	private TaskDetail taskDetil;
	
	@Column(name="Date_Selection")
	private LocalDate dateSelection;
	@Column(name="L1_Issues_Opened")
	private Integer l1IssuesOpened;
	@Column(name="L1_Issues_Closed")
	private Integer l2IssuesOpened;
	@Column(name="L2_Issues_Opened")
	private Integer l1IssuesClosed;
	@Column(name="L2_Issues_Closed")
	private Integer l2IssuesClosed;
	@Column(name="Description")
	private String description;
	@Column(name="Development_In_Per")
	private Float developmentCompletion;
	@Column(name="Remarks")
	private String remarks;
	@Column(name="No_Build_sprint_worked")
	private String numBuildSprintWorkd;
	@Column(name="No_Build_sprint_Completed")
	private String numberBuildSprintCompleted;
	@Column(name="Tests_Per_Day")
	
	private String testsPerDay;
	@Column(name="CREATION_DATE")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name="LAST_UPDATE_DATE")
	@UpdateTimestamp
	private LocalDateTime updateDate;
	@CreatedBy
	 @Column(updatable = false)
  // @Column(name="created_by",updatable = false)
	private String createdBy;
	@LastModifiedBy
	// @Column(name="last_updated_by",updatable = false)
	private String lastUpdatedBy;
		@Column(name="SERVER")
	private String serverName;
	@Column(name="STATUS")
	private String status;
	
	@Column(name="REQUESTED_BY")
	private String requestedBy;
	
	@Column(name="APPROVED_BY")
	private String approvedBy;
	
    
	@Column(name="ISSUE_NUMBER")
	private String issueNumber;
	
	@Column(name="CUSTOMER_NAME")
	private String customerName;
	
	@Column(name="MODULE")
	private String module;
	
	@Column(name="RESOLUTION")
	private String resolution;
	
	@Column(name="TARGET_DATE")
	private LocalDate targetDate;
	
	@Column(name="VIRTUAL_FARMS")
	private String virtualFarms;
	@Column(name="NUM_SERVERS")
	private Integer numOfServers=0;
	@Column(name="NUM_DB")
	private Integer numOfDBs=0;
	@Column(name="SERVER_AVAIL")
	private Float serverAvailablity=(float) 0.0;
	@Column(name="CPU_LOAD")
	private Float cpuLoad=(float) 0.0;
	@Column(name="AVAIL_MEM_SPACE")
	private Float availMemSpace=(float) 0;

	@Column(name="AVAIL_STORAGE_SPACE")
	private Float availStorageSpace=(float) 0;
	
	@Column(name="DB_AVAIL")
	private Float dbAvailablity=(float) 0;
	@Column(name="APP_AVAIL")
	private Float appAvailablity=(float)0;
	@Column(name="NUM_Clients")
	private Integer numClients=0;
	@Column(name="NUM_Service")
	private Integer numService=0;
	@Column(name="NUM_VEHICLES")
	private Integer numVechicles=0;
	
	@Column(name="location")
	private String location;
	
	@Column(name="resource")
	private String resource;
	@Column(name="taskType")
	private String taskType;
	
	@Column(name="activity")
	private String activity;
	@Column(name="account")
	private String account;
	@Column(name="subProject")
	private String subProject;
	@Column(name="subProjectType")
	private String subProjectType;
	@Column(name="member")
	private String member;
	@Column(name="development")
	private String development;
	
	
	
	
}
