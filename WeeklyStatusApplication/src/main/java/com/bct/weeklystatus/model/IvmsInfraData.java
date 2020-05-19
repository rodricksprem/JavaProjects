package com.bct.weeklystatus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



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
public class IvmsInfraData {
	
	public IvmsInfraData(String weekDuration, float appAvailablity, float serverAvailablity , float dbAvailablity, int numberOfClients, int numberOfDb, int numberOfServers, int numberOfVehicles,
			String virtualFarms, String projectName) {
		// TODO Auto-generated constructor stub
		this.weekDuration=weekDuration;
		this.appAvailablity=appAvailablity;
		this.serverAvailablity=serverAvailablity;
		this.dbAvailablity=dbAvailablity;
		this.numberOfClients=numberOfClients;
		this.numberOfDb=numberOfDb;
		this.numberOfServers=numberOfServers;
		this.numberOfVehicles=numberOfVehicles;
		this.virtualFarms=virtualFarms;
		this.projectName=projectName;
		
	}

	private String projectName;
	private String weekDuration;
	private String dateSelection;
	private Float appAvailablity;
	private Float dbAvailablity;
	private Float serverAvailablity;
	private Integer numberOfClients;
	private Integer numberOfDb;
	private Integer numberOfServers;
	private String virtualFarms;
	private Integer numberOfVehicles;
	
	private LocalDate ticketCreatedDate;
	
}
