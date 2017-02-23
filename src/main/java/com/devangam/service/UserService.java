package com.devangam.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.DonationDetailsDTO;
import com.devangam.dto.UserProfileDonationDetails;
import com.devangam.entity.User;
import com.devangam.repository.DonationDetailsRepository;
import com.devangam.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DonationDetailsRepository dontationDetailsRepository;

	public User findUserEntity(String email) {
			return  userRepository.findByUsername(email);
	}

	public void updateUserEntity(User persistObj) {
		userRepository.save(persistObj);
	}

	public List<User> getUserList() {
		return userRepository.findAll();
	}
	@Autowired
	private ObjectMapper objectMapper;

	public List<UserProfileDonationDetails> getUserServices(long userId) throws JsonProcessingException {
		List<UserProfileDonationDetails> listOfDonationDetails = new ArrayList<UserProfileDonationDetails>();
		//listOfDonationDetails.addAll((List<UserProfileDonationDetails> )dontationDetailsRepository.getEducationalDonationDetailsByUserId(userId));
		List<Object[]> patientDetailsFromDatabase = dontationDetailsRepository.getPatientDonationDetailsByUserId(userId);
		List<UserProfileDonationDetails> patientDetails = new ArrayList<UserProfileDonationDetails>();
		patientDetailsFromDatabase.stream().forEach((object) -> { 
				UserProfileDonationDetails userProfileDonationDetails = new UserProfileDonationDetails();
				 userProfileDonationDetails.setName(object[0].toString());
				 userProfileDonationDetails.setPhone_number(object[1].toString());
				 userProfileDonationDetails.setDonation_amt(Double.valueOf(object[2].toString()));
				 userProfileDonationDetails.setHelping_hand_type(object[3].toString());
				 patientDetails.add(userProfileDonationDetails);
		}
		);
		listOfDonationDetails.addAll(patientDetails);
		
		List<Object[]> oldAgeHomeDetails = dontationDetailsRepository.getOldAgeHomeDonationDetailsByUserId(userId);
		List<UserProfileDonationDetails> oldAgeDetails = new ArrayList<UserProfileDonationDetails>();
		oldAgeHomeDetails.stream().forEach((object) -> { 
				UserProfileDonationDetails userProfileDonationDetails = new UserProfileDonationDetails();
				 userProfileDonationDetails.setName(object[0].toString());
				 userProfileDonationDetails.setPhone_number(object[1].toString());
				 userProfileDonationDetails.setDonation_amt(Double.valueOf(object[2].toString()));
				 userProfileDonationDetails.setHelping_hand_type(object[3].toString());
				 oldAgeDetails.add(userProfileDonationDetails);
		}
		);
		listOfDonationDetails.addAll(oldAgeDetails);
		return listOfDonationDetails;
	}

	public List<User> getRegisteredUsers() {
		return userRepository.getRegisteredUsers();
	}
	
	
}
