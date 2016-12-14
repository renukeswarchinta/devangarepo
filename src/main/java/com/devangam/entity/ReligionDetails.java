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


/**
 * The persistent class for the t_religion_details database table.
 * 
 */
@Entity
@Table(name="t_religion_details")
@NamedQuery(name="ReligionDetails.findAll", query="SELECT r FROM ReligionDetails r")
public class ReligionDetails  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="RELIGION_DETAILS_ID")
	@JsonIgnore
	private int religionDetailsId;

	private String dosham;

	private String gothram;

	private String subcaste;

	//bi-directional one-to-one association to User
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonBackReference
	private User user;

	public ReligionDetails() {
	}

	public int getReligionDetailsId() {
		return this.religionDetailsId;
	}

	public void setReligionDetailsId(int religionDetailsId) {
		this.religionDetailsId = religionDetailsId;
	}

	public String getDosham() {
		return this.dosham;
	}

	public void setDosham(String dosham) {
		this.dosham = dosham;
	}

	public String getGothram() {
		return this.gothram;
	}

	public void setGothram(String gothram) {
		this.gothram = gothram;
	}

	public String getSubcaste() {
		return this.subcaste;
	}

	public void setSubcaste(String subcaste) {
		this.subcaste = subcaste;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}