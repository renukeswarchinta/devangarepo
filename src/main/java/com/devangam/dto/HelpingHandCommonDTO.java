package com.devangam.dto;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class HelpingHandCommonDTO {
	private int Id ;
	private Date lastUpdate;
	private String name;
	private String address;
	private String description;
	private String phoneNumber;
	private boolean isEnable;
	private String helpingHandType;
}
