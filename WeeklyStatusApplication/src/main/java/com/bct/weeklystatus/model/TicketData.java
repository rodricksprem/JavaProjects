package com.bct.weeklystatus.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TicketData {
	
	private String projectName;
	private String weekDuration;
	private LocalDate ticketCreatedDate;
	private Integer l1IssuesOpened;
	private Integer l1IssuesClosed;
	private Integer l2IssuesOpened;
	public String getWeekDuration() {
		return weekDuration;
	}
	public void setWeekDuration(String weekDuration) {
		this.weekDuration = weekDuration;
	}
	public LocalDate getTicketCreatedDate() {
		
		//System.out.println("getTicketCreatedDate "+this.toString());
		return this.ticketCreatedDate;
	}
	public void setTicketCreatedDate(LocalDate day) {
		this.ticketCreatedDate = day;
	}
	public Integer getL1IssuesClosed() {
		return l1IssuesClosed;
	}
	public TicketData(LocalDate ticketCreatedDate, Integer l1IssuesOpened,
			Integer l1IssuesClosed, Integer l2IssuesOpened, Integer l2IssuesClosed,String projectName) {
		super();
		this.ticketCreatedDate = ticketCreatedDate;
		System.out.println(this.ticketCreatedDate);
		this.l1IssuesOpened = l1IssuesOpened;
		this.l1IssuesClosed = l1IssuesClosed;
		this.l2IssuesOpened = l2IssuesOpened;
		this.l2IssuesClosed = l2IssuesClosed;
		this.projectName= projectName;
		}
	
	public TicketData() {
		// TODO Auto-generated constructor stub
		
	}
	public void setL1IssuesClosed(Integer l1IssuesClosed) {
		this.l1IssuesClosed = l1IssuesClosed;
	}
	public Integer getL2IssuesOpened() {
		return l2IssuesOpened;
	}
	public void setL2IssuesOpened(Integer l2IssuesOpened) {
		this.l2IssuesOpened = l2IssuesOpened;
	}
	public Integer getL2IssuesClosed() {
		return l2IssuesClosed;
	}
	public void setL2IssuesClosed(Integer l2IssuesClosed) {
		this.l2IssuesClosed = l2IssuesClosed;
	}
	private Integer l2IssuesClosed;
	/**
	 * @return the l1IssuesOpened
	 */
	public Integer getL1IssuesOpened() {
		return l1IssuesOpened;
	}
	/**
	 * @param l1IssuesOpened the l1IssuesOpened to set
	 */
	public void setL1IssuesOpened(Integer l1IssuesOpened) {
		this.l1IssuesOpened = l1IssuesOpened;
	}
	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}
	@Override
	public String toString() {
		return "TicketData [projectName=" + projectName + ", weekDuration=" + weekDuration + ", ticketCreatedDate="
				+ ticketCreatedDate + ", l1IssuesOpened=" + l1IssuesOpened + ", l1IssuesClosed=" + l1IssuesClosed
				+ ", l2IssuesOpened=" + l2IssuesOpened + ", l2IssuesClosed=" + l2IssuesClosed + "]";
	}
	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

}
