package com.bct.weeklystatus.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BCT_PROJECT_USER",uniqueConstraints = {@UniqueConstraint(columnNames = { "username" }),@UniqueConstraint(columnNames = {"email" })})
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class User {
	public User(String name, String username, String email, String password) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.username=username;
		this.email=email;
		this.password=password;
	}
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 @NotBlank
    @Size(min=3, max = 50)
    private String name;
	 
	    @NotBlank
	    @Size(min=3, max = 50)
	    private String username;
	 
	    @NaturalId
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	 
	    
	    @NotBlank
	    @Size(min=6, max = 100)
	    private String password;
	 
	    @ManyToMany(fetch = FetchType.LAZY)
	    @JoinTable(name = "user_roles", 
	      joinColumns = @JoinColumn(name = "user_id"), 
	      inverseJoinColumns = @JoinColumn(name = "role_id"))
	    private Set<Role> roles = new HashSet<>();
	 
	@ManyToMany(cascade = { 
	        CascadeType.ALL
	    })
	    @JoinTable(name = "bct_user_project_details",
	        joinColumns = @JoinColumn(name = "id"),
	        inverseJoinColumns = @JoinColumn(name = "Project_ID"))
	private List<ProjectDetail> projectDetails;
	
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
	
}
