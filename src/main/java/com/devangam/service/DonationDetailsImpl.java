package com.devangam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
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
	
	
	public CommonResponseDTO saveDonationDetails(DonationDetailsDTO donationDetailsDTO) {
		DonationDetails donationDetails =objectMapper.convertValue(donationDetailsDTO, DonationDetails.class);
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
			DonationDetails saveDonationDetails = donationRepository.save(donationDetails);
			commonResponseDTO.setMessage("Successfully saved Donations");
			commonResponseDTO.setStatus("200");
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to load successfully ");
			commonResponseDTO.setStatus("404");
		}
		return commonResponseDTO;
		
	}

	
}
