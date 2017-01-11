package com.devangam.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;


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
	@JsonIgnore
	private int matrimonyId;

	@Column(name="CRATED_FOR")
	private String cratedFor;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date dob;

	private String firstname;

	private String gender;

	private String lastname;

	@Column(name="MOTHER_TOUNGUE")
	private String motherToungue;

	@Column(name="IMAGE_URL")
    private String imageUrl;

	//bi-directional one-to-one association to User
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonBackReference
	private User user;
	
	//@OneToOne(mappedBy="matrimony", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	//@LazyToOne(LazyToOneOption.NO_PROXY)
	@OneToMany(mappedBy="matrimony" , cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JsonManagedReference
	@Setter
	@Getter
	private List<MatrimonyImage> matrimonyImages = new ArrayList<MatrimonyImage>();

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


	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}