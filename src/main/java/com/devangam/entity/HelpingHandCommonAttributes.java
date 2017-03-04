package com.devangam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@MappedSuperclass
@Data
public class HelpingHandCommonAttributes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", updatable = false, nullable = false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATE")
	private Date lastUpdate;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "IS_ENABLE")
	private boolean isEnable;

}
