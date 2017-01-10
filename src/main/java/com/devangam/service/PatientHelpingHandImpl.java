package com.devangam.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.PatientDetailsDTO;
import com.devangam.entity.Education;
import com.devangam.entity.Patients;
import com.devangam.repository.PatientHelpingHandRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PatientHelpingHandImpl {

	@Autowired 
	private PatientHelpingHandRepository patientHelpingHandRepository;
	
	@Autowired 
	private ObjectMapper objectMapper;

	public CommonResponseDTO savePatientHelpingHandDetails(PatientDetailsDTO patientDetailsDTO){
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
		Patients patient = objectMapper.convertValue(patientDetailsDTO, Patients.class);
		Patients p = patientHelpingHandRepository.save(patient);
		commonResponseDTO.setMessage("Saved Patient Details");
		}catch(Exception e){
			commonResponseDTO.setMessage("Error while saveing Patient Details");
		}
		return commonResponseDTO;
	}

	public List<Patients> getPatientDetailsDTO() {
		return patientHelpingHandRepository.findAll();
	}

	
}


