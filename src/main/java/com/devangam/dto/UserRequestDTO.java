package com.devangam.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class UserRequestDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2095259284251994712L;
	private int userId;
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String phoneNumber;
	private String password;
	private String mobileNumber;
	private boolean matrimonyUser;
	private String country;
	private String state;
	private String district;
	private MatrimonyDTO matrimony;
	private PersonalDetailDTO personalDetail;
	private LocationDTO location;
	private ProfessionalDetailsDTO professionalDetail;
	private ReligionDetailsDTO religionDetail;
	private PremiumUserDTO premiumUser;
	private boolean active;
	@JsonIgnore
	private MultipartFile multipartFile;
	@JsonIgnore
	private String userRequestJson;
	
}