package com.devangam.entity;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the t_professional_details database table.
 * 
 */
@Entity
@Table(name="t_professional_details")
@NamedQuery(name="ProfessionalDetails.findAll", query="SELECT p FROM ProfessionalDetails p")
public class ProfessionalDetails  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PROFESSIONAL_DETAILS_ID")
	@JsonIgnore
	private int professionalDetailsId;

	@Column(name="EMPLOYED_IN")
	private String employedIn;

	@Column(name="HIGHEST_EDUCAITON")
	private String highestEducaiton;

	private String income;

	private String occupation;

	//bi-directional one-to-one association to User
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonBackReference(value="userProfessionalDetails")
	private User user;

	public ProfessionalDetails() {
	}

	public int getProfessionalDetailsId() {
		return this.professionalDetailsId;
	}

	public void setProfessionalDetailsId(int professionalDetailsId) {
		this.professionalDetailsId = professionalDetailsId;
	}

	public String getEmployedIn() {
		return this.employedIn;
	}

	public void setEmployedIn(String employedIn) {
		this.employedIn = employedIn;
	}

	public String getHighestEducaiton() {
		return this.highestEducaiton;
	}

	public void setHighestEducaiton(String highestEducaiton) {
		this.highestEducaiton = highestEducaiton;
	}

	public String getIncome() {
		return this.income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}