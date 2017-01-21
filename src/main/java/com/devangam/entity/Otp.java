package com.devangam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;


/**
 * The persistent class for the t_otp database table.
 * 
 */
@Entity
@Table(name="t_otp")
@NamedQuery(name="Otp.findAll", query="SELECT o FROM Otp o")
@Data
public class Otp  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="OTP_ID")
	private int otpId;

	private String otp;

	private String status;

	private String type;

	@Column(name="USER_ID")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VALID_UPTO")
	private Date validUpto;

	@Column(name="VERIFICATION_ID")
	private String verificationId;

}