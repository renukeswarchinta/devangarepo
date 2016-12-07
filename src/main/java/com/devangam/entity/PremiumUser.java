package com.devangam.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the t_premium_users database table.
 * 
 */
@Entity
@Table(name="t_premium_users")
@NamedQuery(name="PremiumUser.findAll", query="SELECT p FROM PremiumUser p")
public class PremiumUser  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PREMIUM_USERS_ID")
	private int premiumUsersId;

	@Column(name="PREMIUM_AMOUNT")
	private int premiumAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PREMIUM_DURATION")
	private Date premiumDuration;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PREMIUM_EXPIRED_ON")
	private Date premiumExpiredOn;

	@Column(name="PREMIUM_PACKAGE")
	private String premiumPackage;

	@Column(name="PREMIUM_TYPE")
	private String premiumType;

	/*//bi-directional one-to-one association to User
	@OneToOne(cascade=CascadeType.ALL,mappedBy="premiumUser")
	@JoinColumn(name="USER_ID")
	private User user;
*/
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