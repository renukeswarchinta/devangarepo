package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class KalyanaMandapasEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name = "MANDAPAM_NAME")
	private String kalyanaMandapamName;
	
	@Column(name = "MANDAPAM_ADDRESS")
	private String kalyanaMandapamAddress;
	
	@Column(name="MANDAPAM_RENT")
	private Double kalyanaMandapamRent;
	
	@Column(name="MANDAPAM_IMAGE_PATH")
	private String imagePath;
	
	
	KalyanaMandapasEntity(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKalyanaMandapamName() {
		return kalyanaMandapamName;
	}

	public void setKalyanaMandapamName(String kalyanaMandapamName) {
		this.kalyanaMandapamName = kalyanaMandapamName;
	}

	public String getKalyanaMandapamAddress() {
		return kalyanaMandapamAddress;
	}

	public void setKalyanaMandapamAddress(String kalyanaMandapamAddress) {
		this.kalyanaMandapamAddress = kalyanaMandapamAddress;
	}

	public Double getKalyanaMandapamRent() {
		return kalyanaMandapamRent;
	}

	public void setKalyanaMandapamRent(Double kalyanaMandapamRent) {
		this.kalyanaMandapamRent = kalyanaMandapamRent;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
