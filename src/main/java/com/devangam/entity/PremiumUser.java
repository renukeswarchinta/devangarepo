package com.devangam.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the t_premium_users database table.
 * 
 */
@Entity
@Table(name="t_premium_users")
@NamedQuery(name="PremiumUser.findAll", query="SELECT p FROM PremiumUser p")
public class PremiumUser  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PREMIUM_USERS_ID")
	@JsonIgnore
	private int premiumUsersId;

	@Column(name="PREMIUM_AMOUNT")
	private int premiumAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PREMIUM_DURATION")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date premiumDuration;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PREMIUM_EXPIRED_ON")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private Date premiumExpiredOn;

	@Column(name="PREMIUM_PACKAGE")
	private String premiumPackage;

	@Column(name="PREMIUM_TYPE")
	private String premiumType;


	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonBackReference
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PremiumUser() {
	}

	public int getPremiumUsersId() {
		return this.premiumUsersId;
	}

	public void setPremiumUsersId(int premiumUsersId) {
		this.premiumUsersId = premiumUsersId;
	}

	public int getPremiumAmount() {
		return this.premiumAmount;
	}

	public void setPremiumAmount(int premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Date getPremiumDuration() {
		return this.premiumDuration;
	}

	public void setPremiumDuration(Date premiumDuration) {
		this.premiumDuration = premiumDuration;
	}

	public Date getPremiumExpiredOn() {
		return this.premiumExpiredOn;
	}

	public void setPremiumExpiredOn(Date premiumExpiredOn) {
		this.premiumExpiredOn = premiumExpiredOn;
	}

	public String getPremiumPackage() {
		return this.premiumPackage;
	}

	public void setPremiumPackage(String premiumPackage) {
		this.premiumPackage = premiumPackage;
	}

	public String getPremiumType() {
		return this.premiumType;
	}

	public void setPremiumType(String premiumType) {
		this.premiumType = premiumType;
	}

/*	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

}