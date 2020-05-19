package com.bct.weeklystatus.entities;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BCT_TASK_DETAILS")
@Getter
@Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "lastUpdatedBy"},
        allowGetters = true
)
@NamedQuery(name="find_all_tasks",query = "select td from TaskDetail td")
public class TaskDetail {
	@Column(name="Task_ID")
	@Id
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "taskdetail_Sequence")
    @SequenceGenerator(name = "taskdetail_Sequence", sequenceName = "TASKDETAIL_SEQ")
   
	private Integer taskId;
	@Column(name="Task_Name")
	private String taskName;
	
	@Column(name="Task_Type")
	private String taskType;
	
	 @JsonBackReference(value="projectdetail_taskdetails")
	@ManyToOne
	 @JoinColumn(name = "Project_ID", nullable = false)
	private ProjectDetail projectDetail;
	
	 @JsonManagedReference(value="taskdetail_projectstatusdetails")
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "taskDetil")
	private List<ProjectStatusDetail> projectStatusDetails;
	
	
	@Column(name="TASK_CREATION_DATE")
	
	private LocalDateTime taskCreationDate;
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
	
		
	}
