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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


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
	@JsonIgnore
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_DATE")
	@JsonIgnore
	private Date createdDate;
	
	@Column(unique=true)
	private String email;

	private String firstname;
	
	@Column(unique=true)
	private String username;

	@Column(name="IS_ACTIVE")
	private boolean active;

	@Column(name="IS_MATRIMONY_USER")
	private boolean matrimonyUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	@JsonIgnore
	private Date lastUpdate;

	private String lastname;

	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;

	private String password;
	
	@Column(name="COUNTRY")
	private String country;

	@Column(name="STATE")
	private String state;
	
	@Column(name="DISTRICT")
	private String district;
	
	
	@ManyToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
	@JsonIgnore
	private List<Role> roles = new ArrayList<Role>();

	@OneToOne(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JsonManagedReference(value="userLocations")
	private Location location;

	@OneToOne(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private Matrimony matrimony;
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	private PersonalDetail personalDetail;
	
	@OneToOne(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JsonManagedReference(value="userPremium")
	private PremiumUser premiumUser;

	@OneToOne(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JsonManagedReference(value="user-Religion")
	private ReligionDetails religionDetail;

	@OneToOne(mappedBy="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY, optional = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@JsonManagedReference(value="userProfessionalDetails")
	private ProfessionalDetails professionalDetail;
	

	public User() {
	}
	public Matrimony getMatrimony() {
		return this.matrimony;
	}

	public void setMatrimony(Matrimony matrimony) {
		this.matrimony = matrimony;
		if (null != matrimony) matrimony.setUser(this);
	}
	
	public int getUserId() {
		return this.userId;
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



	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
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

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Location getLocation() {
		return this.location;
	}
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setLocation(Location location) {
		this.location = location;
		if (null != location) location.setUser(this);
	}


	public PersonalDetail getPersonalDetail() {
		return this.personalDetail;
	}

	public void setPersonalDetail(PersonalDetail personalDetail) {
		this.personalDetail = personalDetail;
		if (null != personalDetail) personalDetail.setUser(this);
	}
	
	public PremiumUser getPremiumUser() {
		return this.premiumUser;
	}

	public void setPremiumUser(PremiumUser premiumUser) {
		this.premiumUser = premiumUser;
		if (null != premiumUser) premiumUser.setUser(this);
	}
	public ReligionDetails getReligionDetail() {
		return this.religionDetail;
	}

	public void setReligionDetail(ReligionDetails religionDetail) {
		this.religionDetail = religionDetail;
		if (null != religionDetail) religionDetail.setUser(this);
	}

	public ProfessionalDetails getProfessionalDetail() {
		return this.professionalDetail;
	}

	public void setProfessionalDetail(ProfessionalDetails professionalDetail) {
		this.professionalDetail = professionalDetail;
		if (null != professionalDetail) professionalDetail.setUser(this);
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isMatrimonyUser() {
		return matrimonyUser;
	}

	public void setMatrimonyUser(boolean matrimonyUser) {
		this.matrimonyUser = matrimonyUser;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}