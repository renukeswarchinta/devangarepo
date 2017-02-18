package com.devangam.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EventDTO {

	private long eventId;
	private String eventName;
	private String eventDescription;
	private String hostedBy;
	private String eventLaunchDate;
	private Date eventPostDate;
	private Date eventPostEndDate;

}
