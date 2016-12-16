package com.devangam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public boolean createEducationDetails(EducationDetailsDTO educationDTO) {
		Education education =objectMapper.convertValue(educationDTO, Education.class);
		Education savedEducation = educationRepository.save(education);
		
		return true;
	}

}
