package com.devangam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.devangam.enums.HelpingHandMasterEnum;

@Entity
public class UserComments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="comments")
	private String comments;
	
	@Enumerated(EnumType.STRING)
    private HelpingHandMasterEnum helpingHandMasterEnum;
	
	
	
}
