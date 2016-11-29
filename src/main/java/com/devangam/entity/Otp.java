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


/**
 * The persistent class for the t_otp database table.
 * 
 */
@Entity
@Table(name="t_otp")
@NamedQuery(name="Otp.findAll", query="SELECT o FROM Otp o")
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
	private int verificationId;

	public Otp() {
	}

	public int getOtpId() {
		return this.otpId;
	}

	public void setOtpId(int otpId) {
		this.otpId = otpId;
	}

	public String getOtp() {
		return this.otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getValidUpto() {
		return this.validUpto;
	}

	public void setValidUpto(Date validUpto) {
		this.validUpto = validUpto;
	}

	public int getVerificationId() {
		return this.verificationId;
	}

	public void setVerificationId(int verificationId) {
		this.verificationId = verificationId;
	}

}