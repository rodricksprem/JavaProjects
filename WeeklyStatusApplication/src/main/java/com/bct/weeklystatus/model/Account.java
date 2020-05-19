package com.bct.weeklystatus.model;

import java.io.Serializable;
import java.util.List;

public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountID;
	
	private String projectName;
	private long projectID;
	private String projectType;
	private int projectStatus;
	private String accountOwner;
	private int accountStatus;
	private String remarks;

	private String accountName;

	private String weekDuration;
	public Account() {
		super();
	}
	public String getAccountID() {
		return accountID;
	}
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return this.accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName= accountName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	public long getProjectID() {
		return projectID;
	}
	public void setProjectID(long projectID) {
		this.projectID = projectID;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public int getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getAccountOwner() {
		return accountOwner;
	}
	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}
	public int getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(int accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public void setWeekDuration(String weekDuration) {
		this.weekDuration=weekDuration;
	}
	public String getWeekDuration() {
		return this.weekDuration;
	}
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", projectName=" + projectName + ", projectID=" + projectID
				+ ", projectType=" + projectType + ", projectStatus=" + projectStatus + ", accountOwner=" + accountOwner
				+ ", accountStatus=" + accountStatus + ", remarks=" + remarks + ", accountName=" + accountName
				+ ", weekDuration=" + weekDuration + "]";
	}
	
}
	