package com.devangam.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String mobileNumber;
	private boolean matrimonyUser;
	private MatrimonyDTO matrimony;
	private PersonalDetailDTO personalDetail;
	private LocationDTO location;
	private ProfessionalDetailsDTO professionalDetail;
	private ReligionDetailsDTO religionDetail;
	private PremiumUserDTO premiumUser;
	
}