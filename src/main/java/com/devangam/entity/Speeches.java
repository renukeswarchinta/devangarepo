package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="SPEECHES")
public class Speeches {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@JsonIgnore
	private int id;
	@Column(name="SPEECH_TITLE")
	private String speecheTitle;
	@Column(name="SPEECH_DESCRIPTION")
	private String speecheDescription;
	@Column(name="SPEECH_BY")
	private String speechBy;
	
	Speeches(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpeecheTitle() {
		return speecheTitle;
	}

	public void setSpeecheTitle(String speecheTitle) {
		this.speecheTitle = speecheTitle;
	}

	public String getSpeecheDescription() {
		return speecheDescription;
	}

	public void setSpeecheDescription(String speecheDescription) {
		this.speecheDescription = speecheDescription;
	}

	public String getSpeechBy() {
		return speechBy;
	}

	public void setSpeechBy(String speechBy) {
		this.speechBy = speechBy;
	}
	
	
	
	
}
