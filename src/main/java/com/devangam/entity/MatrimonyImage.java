package com.devangam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_MATRIMONY_IMAGE")
public class MatrimonyImage {

	public MatrimonyImage() {
	}

	public MatrimonyImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MatrimonyImage(String key, String imageUrl) {
		this.key = key;
		this.imageUrl = imageUrl;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATRIMONY_IMAGE_ID")
	private int matrimonyImageId;

	@Column(name = "IMAGE_KEY")
	private String key;

	@Column(name = "IMAGE_URL")
	private String imageUrl;
	
	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
	@JsonBackReference
	private User user;

	public int getMatrimonyImageId() {
		return matrimonyImageId;
	}

	public void setMatrimonyImageId(int matrimonyImageId) {
		this.matrimonyImageId = matrimonyImageId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
