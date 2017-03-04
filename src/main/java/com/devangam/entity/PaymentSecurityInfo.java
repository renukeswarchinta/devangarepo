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
@Table(name = "t_payment_security_info")
@Data
public class PaymentSecurityInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PAYMENT_SECURITY_INFO_ID")
	private int paymentSecurityInfoId;
	
	@Column(name = "key_id")
	private String keyId;
	
	@Column(name = "key_secret")
	private String keySecret;

	@Column(name = "ENABLE")
	private boolean enable;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

}