package com.devangam.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the t_role database table.
 * 
 */
@Entity
@Table(name="t_role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role  {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROLE_ID")
	private int roleId;

	@Column(name="ROLE_DESCRIPTION")
	private String roleDescription;

	@Column(name="ROLE_NAME")
	private String roleName;

	//bi-directional many-to-many association to User
	@ManyToMany(mappedBy="roles", cascade={CascadeType.ALL})
	private Set<User> users;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}