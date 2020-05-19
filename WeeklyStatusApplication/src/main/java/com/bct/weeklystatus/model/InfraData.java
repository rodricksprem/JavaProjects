package com.bct.weeklystatus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InfraData {
	
	private String projectName;
	private String weekDuration;
	private String dateSelection;
	private Float appAvailablity;
	private Float availMemSpace;
	private Float availStorageSpace;
	private Float availCPULoad;
	private Integer numberOfDb;
	private Integer numberOfServices;
	private String serverName;
	private LocalDate ticketCreatedDate;
	public InfraData(String weekDuration, float appAvailablity, float availMemSpace, float availStorageSpace, float availCPULoad, int numberOfDB,int numberOfServices, String serverName,String projectName) {
		// TODO Auto-generated constructor stub
		this.weekDuration=weekDuration;
		this.appAvailablity=appAvailablity;
		this.availMemSpace=availMemSpace;
		this.availStorageSpace=availStorageSpace;
		this.availCPULoad=availCPULoad;
		this.numberOfDb=numberOfDB;
		this.numberOfServices=numberOfServices;
		this.projectName=projectName;
		this.serverName=serverName;
	}

}
