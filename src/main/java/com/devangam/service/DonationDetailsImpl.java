package com.devangam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.DonationDetailsDTO;
import com.devangam.entity.DonationDetails;
import com.devangam.repository.DonationDetailsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DonationDetailsImpl {

	@Autowired
	private DonationDetailsRepository donationRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	public void saveDonationDetails(DonationDetailsDTO donationDetailsDTO) {
		DonationDetails donationDetails =objectMapper.convertValue(donationDetailsDTO, DonationDetails.class);
		DonationDetails saveDonationDetails = donationRepository.save(donationDetails);
		
	}

	
}
