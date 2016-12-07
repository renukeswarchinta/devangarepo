package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HELPING_HAND_USER_TRANSACTON")
public class MasterEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int masterId;
	@Column(name="HH_CATEGORY_ID")
	private int id;
	@Column(name="USER_ID")
	private int userId;
	@Column(name="HH_TYPE")
	private String hhType;
	@Column(name="AMT_TRANSFFERED")
	private double amtTransffered;
	
	
	
	public int getMasterId() {
		return masterId;
	}
	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getHhType() {
		return hhType;
	}
	public void setHhType(String hhType) {
		this.hhType = hhType;
	}
	public double getAmtTransffered() {
		return amtTransffered;
	}
	public void setAmtTransffered(double amtTransffered) {
		this.amtTransffered = amtTransffered;
	}
	
	
}
