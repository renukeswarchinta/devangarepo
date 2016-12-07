package com.devangam.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the t_personal_details database table.
 * 
 */
@Entity
@Table(name="t_personal_details")
@NamedQuery(name="PersonalDetail.findAll", query="SELECT p FROM PersonalDetail p")
public class PersonalDetail  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PERSONAL_DETAILS_ID")
	private int personalDetailsId;

	private String disability;

	@Column(name="FAMILY_STATUS")
	private String familyStatus;

	@Column(name="FAMILY_TYPE")
	private String familyType;

	@Column(name="FAMILY_VALUES")
	private String familyValues;

	private String height;

	@Column(name="MARITAL_STATUS")
	private String maritalStatus;

	//bi-directional one-to-one association to User
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_ID")
	private User user;

	public PersonalDetail() {
	}

	public int getPersonalDetailsId() {
		return this.personalDetailsId;
	}

	public void setPersonalDetailsId(int personalDetailsId) {
		this.personalDetailsId = personalDetailsId;
	}

	public String getDisability() {
		return this.disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public String getFamilyStatus() {
		return this.familyStatus;
	}

	public void setFamilyStatus(String familyStatus) {
		this.familyStatus = familyStatus;
	}

	public String getFamilyType() {
		return this.familyType;
	}

	public void setFamilyType(String familyType) {
		this.familyType = familyType;
	}

	public String getFamilyValues() {
		return this.familyValues;
	}

	public void setFamilyValues(String familyValues) {
		this.familyValues = familyValues;
	}

	public String getHeight() {
		return this.height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}