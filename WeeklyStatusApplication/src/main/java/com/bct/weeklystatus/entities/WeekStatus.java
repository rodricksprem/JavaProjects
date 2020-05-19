package com.bct.weeklystatus.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Embeddable  
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WeekStatus {

	@Column(name="week_duration")
	private String weekduration;
	@Column(name="project_status")
	private Integer projectStatus;
}
