package com.devangam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EVENTS")
public class Events {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENT_ID")
	private Long id;
	
	@Column(name="EVENT_NAME")
	private String eventName;
	@Column(name="EVENT_DESCRIPTION")
	private String eventDescription;

	@Column(name="HOSTED_BY")
	private String hostedBy;

	@Column(name="EVENT_LAUNCH_DATE")
	private String eventLaunchDate;

	@Column(name="EVENT_POST_START_DATE")
	private Date eventPostDate;
	
	@Column(name="EVENT_POST_END_DATE")
	private Date eventPostEndDate;
	
	private boolean isActive;
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	Events(){}

	public long getEventId() {
		return id;
	}

	public void setEventId(long eventId) {
		this.id = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getHostedBy() {
		return hostedBy;
	}

	public void setHostedBy(String hostedBy) {
		this.hostedBy = hostedBy;
	}

	public String getEventLaunchDate() {
		return eventLaunchDate;
	}

	public void setEventLaunchDate(String eventLaunchDate) {
		this.eventLaunchDate = eventLaunchDate;
	}

	public Date getEventPostDate() {
		return eventPostDate;
	}

	public void setEventPostDate(Date eventPostDate) {
		this.eventPostDate = eventPostDate;
	}

	public Date getEventPostEndDate() {
		return eventPostEndDate;
	}

	public void setEventPostEndDate(Date eventPostEndDate) {
		this.eventPostEndDate = eventPostEndDate;
	}
	
	
	
	
	

}
