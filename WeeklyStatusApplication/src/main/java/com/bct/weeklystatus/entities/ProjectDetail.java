package com.bct.weeklystatus.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BCT_PROJECT_DETAIL")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@NamedQuery(name="find_all_projects",query = "select pd from ProjectDetail pd")
public class ProjectDetail {
	@Column(name="Project_ID")
	@Id
	private Long projectId;
	
	@Column(name="Project_Name")
	private String projectName;
	
	@ManyToMany(mappedBy="projectDetails")
	private List<User> users;
	
	 @JsonManagedReference(value="projectdetail_taskdetails")
	@OneToMany(cascade = CascadeType.ALL,
            mappedBy = "projectDetail")
	private List<TaskDetail> taskDetails;
	
	/*
	 * @JsonManagedReference(value="projectdetail_projectstatusdetails")
	 * 
	 * @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy =
	 * "projectDetail") private List<ProjectStatusDetail> projectStatusDetails;
	 */
	
	@JsonBackReference(value="account_projectdetail")
	@ManyToOne
	 @JoinColumn(name = "Account_ID", nullable = false)
	private Account account;
	
	@Column(name="Project_Owner")
	private String projectOwner;
	@Column(name="project_start_date")
	private LocalDate projectStartDate;
	@Column(name="project_end_date")
	private LocalDate projectEndDate;
	@Column(name="CREATION_DATE")
	@CreationTimestamp
	private LocalDateTime creationDate;
	@Column(name="LAST_UPDATE_DATE")
	@UpdateTimestamp
	private LocalDateTime updateDate;
	@CreatedBy
    @Column(updatable = false)
	private String createdBy;
	@LastModifiedBy
	private String lastUpdatedBy;

	@Column(name="PROJECT_TYPE")
	private String projectType;
	  @ElementCollection
	@CollectionTable(name = "weekDurations", joinColumns = @JoinColumn(name = "PROJECT_ID"))
	
	  @AttributeOverrides({
		  @AttributeOverride(name="weekDuration", column=@Column(name="WEEK_DURATION")),
		  @AttributeOverride(name="projectStatus", column=@Column(name="PROJECT_STATUS"))
	  })
	private List<WeekStatus> weekDuration;
}
