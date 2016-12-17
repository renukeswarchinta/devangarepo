package com.devangam.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.PatientDetailsDTO;
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

	public boolean savePatientHelpingHandDetails(PatientDetailsDTO patientDetailsDTO){
		Patients patient = objectMapper.convertValue(patientDetailsDTO, Patients.class);
		Patients p = patientHelpingHandRepository.save(patient);
		return true;
	}
}

