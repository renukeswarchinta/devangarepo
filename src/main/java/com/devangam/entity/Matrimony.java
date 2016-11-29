package com.devangam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the t_matrimony database table.
 * 
 */
@Entity
@Table(name="t_matrimony")
@NamedQuery(name="Matrimony.findAll", query="SELECT m FROM Matrimony m")
public class Matrimony  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="MATRIMONY_ID")
	private int matrimonyId;

	@Column(name="CRATED_FOR")
	private String cratedFor;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String firstname;

	private String gender;

	private String lastname;

	@Column(name="MOTHER_TOUNGUE")
	private String motherToungue;

	@Lob
	private byte[] photo;

	//bi-directional one-to-one association to User
	@OneToOne(mappedBy="matrimony")
	private User user;

	public Matrimony() {
	}

	public int getMatrimonyId() {
		return this.matrimonyId;
	}

	public void setMatrimonyId(int matrimonyId) {
		this.matrimonyId = matrimonyId;
	}

	public String getCratedFor() {
		return this.cratedFor;
	}

	public void setCratedFor(String cratedFor) {
		this.cratedFor = cratedFor;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMotherToungue() {
		return this.motherToungue;
	}

	public void setMotherToungue(String motherToungue) {
		this.motherToungue = motherToungue;
	}

	public byte[] getPhoto() {
		return this.photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}