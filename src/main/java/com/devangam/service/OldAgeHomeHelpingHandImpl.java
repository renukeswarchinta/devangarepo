package com.devangam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public boolean saveOldAgeHomeHelpingHandDetails(OldAgeHomeHelpingHandDTO oldAgeHomeHelpingHand){
		OldageHome oldAgeHome = objectMapper.convertValue(oldAgeHomeHelpingHand, OldageHome.class);
		oldAgeHomeHelpingHandRepo.save(oldAgeHome);
		return true;
	}
}
