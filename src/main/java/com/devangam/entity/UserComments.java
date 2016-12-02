package com.devangam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserComments {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	
}
