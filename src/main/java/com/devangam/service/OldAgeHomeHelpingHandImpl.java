package com.devangam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.OldAgeHomeHelpingHandDTO;
import com.devangam.entity.OldageHome;
import com.devangam.repository.OldAgeHomeHelpingHandRpository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OldAgeHomeHelpingHandImpl {

	@Autowired 
	private OldAgeHomeHelpingHandRpository oldAgeHomeHelpingHandRepo;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public CommonResponseDTO saveOldAgeHomeHelpingHandDetails(OldAgeHomeHelpingHandDTO oldAgeHomeHelpingHand){
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
			OldageHome oldAgeHome = objectMapper.convertValue(oldAgeHomeHelpingHand, OldageHome.class);
			oldAgeHomeHelpingHandRepo.save(oldAgeHome);
			commonResponseDTO.setMessage("Saved OldAge home details");
			
		}catch(Exception e){
			commonResponseDTO.setMessage("Error while saving OldAge Home details ");
		}
		return commonResponseDTO;
	}
}
