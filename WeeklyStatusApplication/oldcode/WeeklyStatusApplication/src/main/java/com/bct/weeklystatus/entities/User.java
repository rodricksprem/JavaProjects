package com.bct.weeklystatus.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="BCT_USER")
@NamedQuery(name="find_all_users",query = "select u from User u")
public class User {
	@Column(name="user_id")
	@Id
	private String userID;
	@Column(name="USER_NAME")
	private String userName;
	@ManyToMany
	private List<ProjectDetail> projectDetails;
	@Column(name="PASSWORD")
	private String password;
	 @Transient
	    private String passwordConfirm;
	 @ManyToMany
	    private Set<Role> roles;
 
	@Column(name="USER_EMAIL_ADDRESS")
	private String userEmailAddress;
	@Column(name="user_role")
	private String userRole;
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
	public String getUserID() {
		return userID;
	}
	
	
	public User() {
	}


	

	public User(String userID, String userName, List<ProjectDetail> projectDetails, String password,
			String passwordConfirm, Set<Role> roles, String userEmailAddress, String userRole,
			LocalDateTime creationDate, LocalDateTime updateDate, String createdBy, String lastUpdatedBy) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.projectDetails = projectDetails;
		this.password = password;
		this.passwordConfirm = passwordConfirm;
		this.roles = roles;
		this.userEmailAddress = userEmailAddress;
		this.userRole = userRole;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public List<ProjectDetail> getProjectDetails() {
		return projectDetails;
	}


	public void setProjectDetails(List<ProjectDetail> projectDetails) {
		this.projectDetails = projectDetails;
	}

	public void addProjectDetail(ProjectDetail projectDetail) {
		this.projectDetails.add(projectDetail);
	}
	public void removeProjectDetail(ProjectDetail projectDetail) {
		this.projectDetails.remove(projectDetail);
	}
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserEmailAddress() {
		return userEmailAddress;
	}


	public void setUserEmailAddress(String userEmailAddress) {
		this.userEmailAddress = userEmailAddress;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
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


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getPasswordConfirm() {
		return passwordConfirm;
	}


	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
}
