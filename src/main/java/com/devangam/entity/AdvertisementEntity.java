package com.devangam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Advertisement")
public class AdvertisementEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@JsonIgnore
	private int id;

	@Column(name="ADVERTISEMENT_IMAGE_PATH")
	private String imagePath;
	
	// is it whether BANNER, ADVERTISEMENT etc..
	@Column(name="ADVERTISEMENT_TYPE")
	private String advertisementType;
	
	@Column(name="ADVERTISEMENT_START_DATE")
	private Date startDate;
	
	@Column(name="ADVERTISEMENT_END_DATE")
	private Date endDate;
	@Column(name="ADVERTISEMENT_COST")
	private double advertisementCost;
	
	@Column(name="IS_EXPIRED")
	private boolean expired;
	
	@Column(name="DAYS_TO_BE_POSTED")
	private int noOfDaysToBePosted;
	
	
	public AdvertisementEntity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getAdvertisementType() {
		return advertisementType;
	}

	public void setAdvertisementType(String advertisementType) {
		this.advertisementType = advertisementType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getAdvertisementCost() {
		return advertisementCost;
	}

	public void setAdvertisementCost(double advertisementCost) {
		this.advertisementCost = advertisementCost;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	
}
