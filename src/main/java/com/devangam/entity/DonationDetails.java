package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DONATION_DETAILS")
public class DonationDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="HELPING_HAND_ID")
	private int helpingHandId;
	
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="HELPING_HAND_TYPE")
	private String helpingHandType;
	
	@Column(name="PAYMENT_ID")
	private String paymentId;
	
	@Column(name="DONATION_AMT")
	private double amountReceived;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;

	public DonationDetails(){}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHelpingHandId() {
		return helpingHandId;
	}

	public void setHelpingHandId(int helpingHandId) {
		this.helpingHandId = helpingHandId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHelpingHandType() {
		return helpingHandType;
	}

	public void setHelpingHandType(String helpingHandType) {
		this.helpingHandType = helpingHandType;
	}

	public double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}