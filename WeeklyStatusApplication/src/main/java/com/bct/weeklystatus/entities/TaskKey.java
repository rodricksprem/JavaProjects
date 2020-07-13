package com.bct.weeklystatus.entities;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.ToString;
@ToString
@EqualsAndHashCode
public class TaskKey  implements Serializable {
	
	private Integer taskId;
	private String taskName;
 
    // default constructor
 
    public TaskKey(Integer taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }
 
    // equals() and hashCode()
}