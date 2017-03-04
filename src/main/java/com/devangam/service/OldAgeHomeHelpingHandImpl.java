package com.devangam.service;

import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.constants.DevangamConstants;
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

	public List<OldageHome> getOldAgeHomeDetailsDTO() {
		return oldAgeHomeHelpingHandRepo.findAll();
	}
	
	public CommonResponseDTO updateOldAgeHomeDetails(OldAgeHomeHelpingHandDTO oldAgeHome) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
				OldageHome oldAgeHomeEntity = oldAgeHomeHelpingHandRepo.findOne(oldAgeHome.getId());
				oldAgeHomeEntity.setName(oldAgeHome.getName());
				oldAgeHomeEntity.setAddress(oldAgeHome.getAddress());
				oldAgeHomeEntity.setDescription(oldAgeHome.getDescription());
				oldAgeHomeEntity.setNoOfPeople(oldAgeHome.getNoOfPeople());
				oldAgeHomeEntity.setOldageHomeGovtId(oldAgeHome.getOldageHomeGovtId());
				oldAgeHomeEntity.setPhoneNumber(oldAgeHome.getPhoneNumber());
				oldAgeHomeEntity.setLastUpdate(new Date());
				oldAgeHomeHelpingHandRepo.save(oldAgeHomeEntity);
				commonResponseDTO.setMessage("Success");
				
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to update ");
		}
		return commonResponseDTO;
	}

	public CommonResponseDTO disableOldAgeHomeDetailsById(String helpingHandId) {
		//return oldAgeHomeHelpingHandRepo.disableOldAgeHomeDetailsById(helpingHandId,disable);
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
			oldAgeHomeHelpingHandRepo.delete(helpingHandId);
			commonResponseDTO.setStatus(SUCCESS);
		}catch(Exception e){
			commonResponseDTO.setStatus(DevangamConstants.FAIL);
		}
		return commonResponseDTO;
	}
}
