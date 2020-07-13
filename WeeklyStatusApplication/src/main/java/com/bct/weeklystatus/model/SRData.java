package com.bct.weeklystatus.model;

import java.time.LocalDate;

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
public class SRData {
	private String projectName;
	private LocalDate srCreatedDate;
	private Integer srOpened;
	private Integer srClosed=0;
	private String srCategory;
	
	public SRData(LocalDate srCreatedDate, String srCategory,Integer srOpened,
			Integer srClosed,String projectName) {
		super();
		this.srCreatedDate = srCreatedDate;
		this.srOpened = srOpened;
		this.srClosed = srClosed;
		this.srCategory = srCategory;
		
		this.projectName= projectName;
		}
	
}
