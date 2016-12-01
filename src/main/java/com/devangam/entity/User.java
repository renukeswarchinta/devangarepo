package com.devangam.entity;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


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
	
	private String username;

	@Column(name="IS_ACTIVE")
	private boolean isActive;

	@Column(name="IS_MATRIMONY_USER")
	private boolean isMatrimonyUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPDATE")
	private Date lastUpdate;

	private String lastname;

	@Column(name="MOBILE_NUMBER")
	private String mobileNumber;

	private String password;

	//bi-directional many-to-many association to Role
	//@ManyToMany(mappedBy="users")
	
	
	
	
	@ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinTable(
            name = "USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")})
	private Set<Role> roles;

	//bi-directional one-to-one association to Location
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private Location location;

	//bi-directional one-to-one association to Matrimony
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private Matrimony matrimony;

	//bi-directional one-to-one association to PersonalDetail
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private PersonalDetail personalDetail;

	//bi-directional one-to-one association to PremiumUser
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private PremiumUser premiumUser;

	//bi-directional one-to-one association to ReligionDetails
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private ReligionDetails religionDetail;

	//bi-directional one-to-one association to ProfessionalDetails
	@OneToOne
	@JoinColumn(name="USER_ID", referencedColumnName="USER_ID")
	private ProfessionalDetails professionalDetail;

	public User() {
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

	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Matrimony getMatrimony() {
		return this.matrimony;
	}

	public void setMatrimony(Matrimony matrimony) {
		this.matrimony = matrimony;
	}

	public PersonalDetail getPersonalDetail() {
		return this.personalDetail;
	}

	public void setPersonalDetail(PersonalDetail personalDetail) {
		this.personalDetail = personalDetail;
	}

	public PremiumUser getPremiumUser() {
		return this.premiumUser;
	}

	public void setPremiumUser(PremiumUser premiumUser) {
		this.premiumUser = premiumUser;
	}

	public ReligionDetails getReligionDetail() {
		return this.religionDetail;
	}

	public void setReligionDetail(ReligionDetails religionDetail) {
		this.religionDetail = religionDetail;
	}

	public ProfessionalDetails getProfessionalDetail() {
		return this.professionalDetail;
	}

	public void setProfessionalDetail(ProfessionalDetails professionalDetail) {
		this.professionalDetail = professionalDetail;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isMatrimonyUser() {
		return isMatrimonyUser;
	}

	public void setMatrimonyUser(boolean isMatrimonyUser) {
		this.isMatrimonyUser = isMatrimonyUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}