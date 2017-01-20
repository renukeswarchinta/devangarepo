package com.devangam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.JobOpportunityDTO;
import com.devangam.entity.JobOpportunities;
import com.devangam.repository.JobOpportunityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JobOpportunitiesServiceImpl {

	@Autowired
	private JobOpportunityRepository jobOpportunityRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public CommonResponseDTO saveJobOpportunities(JobOpportunityDTO jobOpportunityDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
		JobOpportunities jobOpportunities =objectMapper.convertValue(jobOpportunityDTO, JobOpportunities.class);
		JobOpportunities savedJobOpportunities = jobOpportunityRepository.save(jobOpportunities);
		commonResponseDTO.setMessage("Saved JobOpportunities details");
		}catch(Exception exception ){
			commonResponseDTO.setMessage("Exception while saving Educaiton details ");
			commonResponseDTO.setStatus("400");
		}
		return commonResponseDTO;
	}

	public List<JobOpportunities> getJobOpportunities() {
		return jobOpportunityRepository.findAll();
	}

	public CommonResponseDTO getJobOpportunities(JobOpportunityDTO jobOpportunityDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
