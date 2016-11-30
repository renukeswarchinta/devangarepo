package com.devangam.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the t_user database table.
 * 
 */
@Entity
@Table(name="t_user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USER_ID")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String email;

	private String firstname;

	@Column(name="IS_ACTIVE")
	private boolean isActive; //

	@Column(name="IS_MATRIMONY_USER")
	private int isMatrimonyUser;

	private String lastname;

	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;

	private String password;
	
	private String username;

	//bi-directional many-to-many association to Role
	@ManyToMany
	@JoinTable(
		name="t_user_role"
		, joinColumns={
			@JoinColumn(name="USER_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		)
	private Set<Role> roles;

	//bi-directional one-to-one association to Matrimony
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private Matrimony matrimony;


	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getIsMatrimonyUser() {
		return this.isMatrimonyUser;
	}

	public void setIsMatrimonyUser(int isMatrimonyUser) {
		this.isMatrimonyUser = isMatrimonyUser;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Matrimony getMatrimony() {
		return this.matrimony;
	}

	public void setMatrimony(Matrimony matrimony) {
		this.matrimony = matrimony;
	}


}