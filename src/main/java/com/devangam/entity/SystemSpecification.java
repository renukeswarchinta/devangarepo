package com.devangam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "t_system_specification")
@Data
public class SystemSpecification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "System_Specification_ID")
	private int systemSpecificationId;
	
	@Column(name = "TYPE")
	private String type;

	@Column(name = "ENABLE")
	private boolean enable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private boolean updatedDate;

}