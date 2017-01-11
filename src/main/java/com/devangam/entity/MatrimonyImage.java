package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_MATRIMONY_IMAGE")
public class MatrimonyImage {

	public MatrimonyImage() {
	}

	public MatrimonyImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public MatrimonyImage(String key, String imageUrl,String imageType) {
		this.key = key;
		this.imageUrl = imageUrl;
		this.imageType = imageType;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MATRIMONY_IMAGE_ID")
	private int matrimonyImageId;

	@Column(name = "IMAGE_KEY")
	private String key;

	@Column(name = "IMAGE_URL")
	private String imageUrl;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MATRIMONY_ID")
	@JsonBackReference
	private Matrimony matrimony;
	
	@JoinColumn(name="IMAGE_TYPE")
	@Getter
	@Setter
	private String imageType;

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

	public Matrimony getMatrimony() {
		return matrimony;
	}

	public void setMatrimony(Matrimony matrimony) {
		this.matrimony = matrimony;
	}

}
