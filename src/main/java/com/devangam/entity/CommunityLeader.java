package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CommunityLeader {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
	private long id;
	@Column(name="name",nullable =false)
	private String name;
	@Column(name="imagePath")
	private String imagePath;
	@Column(name="currentDesignation")
	private String currentDesignation;
	@Column(name="description")
	private String description;
	
	CommunityLeader(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getCurrentDesignation() {
		return currentDesignation;
	}

	public void setCurrentDesignation(String currentDesignation) {
		this.currentDesignation = currentDesignation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
