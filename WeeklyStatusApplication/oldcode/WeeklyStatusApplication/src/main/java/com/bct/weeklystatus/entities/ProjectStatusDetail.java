package com.bct.weeklystatus.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

@Entity
@Table(name="BCT_PROJECT_STATUS_DETAILS")
@NamedQuery(name="find_all_status",query = "select ps from ProjectStatusDetail ps")
public class ProjectStatusDetail {
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "projectstatus_Sequence")
    @SequenceGenerator(name = "projectstatus_Sequence", sequenceName = "PROJECTSTATUS_SEQ")
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="projectId")
	private ProjectDetail projectDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="task_id")
	private TaskDetail taskDetil;
	
	@Column(name="Date_Selection")
	private LocalDateTime dateSelection;
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
	private Integer developmentCompletion;
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
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="LAST_UPDATED_BY")
	private String lastUpdatedBy;
	public ProjectStatusDetail() {
	
	}
	public ProjectStatusDetail(Long id, ProjectDetail projectDetail, TaskDetail taskDetil, LocalDateTime dateSelection,
			Integer l1IssuesOpened, Integer l2IssuesOpened, Integer l1IssuesClosed, Integer l2IssuesClosed,
			String description, Integer developmentCompletion, String remarks, String numBuildSprintWorkd,
			String numberBuildSprintCompleted, String testsPerDay, LocalDateTime creationDate, LocalDateTime updateDate,
			String createdBy, String lastUpdatedBy) {
		super();
		this.id = id;
		this.projectDetail = projectDetail;
		this.taskDetil = taskDetil;
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
	public ProjectDetail getProjectDetail() {
		return projectDetail;
	}
	public void setProjectDetail(ProjectDetail projectDetail) {
		this.projectDetail = projectDetail;
	}
	public TaskDetail getTaskDetil() {
		return taskDetil;
	}
	public void setTaskDetil(TaskDetail taskDetil) {
		this.taskDetil = taskDetil;
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
