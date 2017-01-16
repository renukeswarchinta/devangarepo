package com.devangam.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class HelpingHandCommonDTO {
	private int Id ;
	private Date lastUpdate;
	private String name;
	private String address;
	private String description;
	private Integer phoneNumber;
	private boolean isEnable;
}
