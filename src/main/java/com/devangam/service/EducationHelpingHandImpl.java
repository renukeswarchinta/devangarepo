package com.devangam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.EducationDetailsDTO;
import com.devangam.entity.Education;
import com.devangam.repository.EducationHelpingHandRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
@Transactional
public class EducationHelpingHandImpl implements IHelpingHandService {

	@Autowired
	private EducationHelpingHandRepository educationRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public CommonResponseDTO createEducationDetails(EducationDetailsDTO educationDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
		Education education =objectMapper.convertValue(educationDTO, Education.class);
		Education savedEducation = educationRepository.save(education);
		commonResponseDTO.setMessage("Saved Educaiton details");
		}catch(Exception exception ){
			commonResponseDTO.setMessage("Exception while saving Educaiton details ");
			commonResponseDTO.setStatus("400");
		}
		return commonResponseDTO;
	}

	public List<Education> getEducationDetails() {
		return educationRepository.findActiveEducationDetails();
	}

	public CommonResponseDTO updateEducationHelpingHandDetails(EducationDetailsDTO educationDetailsDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
				Education education = educationRepository.findOne(educationDetailsDTO.getId());
				education.setName(educationDetailsDTO.getName());
				education.setAddress(educationDetailsDTO.getAddress());
				education.setCurrentEducation(educationDetailsDTO.getCurrentEducation());
				education.setDescription(educationDetailsDTO.getDescription());
				education.setFamilyAnnualIncome(educationDetailsDTO.getFamilyAnnualIncome());
				education.setFamilyStatus(educationDetailsDTO.getFamilyStatus());
				education.setLastUpdate(educationDetailsDTO.getLastUpdate());
				education.setMerit(educationDetailsDTO.getMerit());
				education.setPhoneNumber(educationDetailsDTO.getPhoneNumber());
				educationRepository.save(education);
				commonResponseDTO.setMessage("Success");
				
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to update ");
		}
		return commonResponseDTO;
	}

	public int disableEducationDetailsById(String helpingHandId,int disable) {
		return educationRepository.disableEducationDetailsById(helpingHandId,disable);
	}


}
