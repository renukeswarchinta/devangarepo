package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.constants.DevangamConstants;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.PatientDetailsDTO;
import com.devangam.entity.Patients;
import com.devangam.repository.PatientHelpingHandRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PatientHelpingHandImpl {

	@Autowired 
	private PatientHelpingHandRepository patientHelpingHandRepository;
	
	@Autowired 
	private ObjectMapper objectMapper;

	public CommonResponseDTO savePatientHelpingHandDetails(PatientDetailsDTO patientDetailsDTO){
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
		Patients patient = objectMapper.convertValue(patientDetailsDTO, Patients.class);
		patientHelpingHandRepository.save(patient);
		commonResponseDTO.setMessage("Saved Patient Details");
		commonResponseDTO.setStatus(SUCCESS);
		}catch(Exception exception){
			commonResponseDTO.setMessage("Error while saveing Patient Details");
			commonResponseDTO.setStatus(FAIL);
			log.error("Save Patient Helping Hand Details failed",exception);
		}
		return commonResponseDTO;
	}

	public List<PatientDetailsDTO> getPatientDetailsDTO() {
		List<PatientDetailsDTO> listOfPatients = new ArrayList<PatientDetailsDTO>();
		List<Patients> listOfPatienst =  patientHelpingHandRepository.findAll();
		convertEntityToDTO(listOfPatients, listOfPatienst);
		return listOfPatients;
	}

	private List<PatientDetailsDTO> convertEntityToDTO(List<PatientDetailsDTO> listOfPatients, List<Patients> listOfPatienst) {
		listOfPatienst.stream().forEach(patient -> {
			PatientDetailsDTO p = objectMapper.convertValue(patient, PatientDetailsDTO.class);
			listOfPatients.add(p);
		});
		return listOfPatients;
	}

	public CommonResponseDTO updatePatientDetails(PatientDetailsDTO patient) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
				Patients patientEntity = patientHelpingHandRepository.findOne(patient.getId());
				patientEntity.setAddress(patient.getAddress());
				patientEntity.setDescription(patient.getDescription());
				patientEntity.setName(patient.getName());
				patientEntity.setPatientAge(patient.getPatientAge());
				patientEntity.setCurrentHealthCondition(patient.getCurrentHealthCondition());
				patientEntity.setPhoneNumber(patient.getPhoneNumber());
				patientEntity.setSuffereingFromdisease(patient.getSuffereingFromdisease());
				patientEntity.setLastUpdate(new Date());
				patientHelpingHandRepository.save(patientEntity);
				commonResponseDTO.setMessage("Success");
				commonResponseDTO.setStatus(SUCCESS);
				
		}catch(Exception e){
			commonResponseDTO.setMessage("Failed to update ");
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;
		
	}

	public CommonResponseDTO disablePatientDetailsById(String helpingHandId) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			patientHelpingHandRepository.delete(Integer.valueOf(helpingHandId));
			commonResponseDTO.setStatus(SUCCESS);
			commonResponseDTO.setMessage("Successfully disabled Patient Details");
		} catch (Exception exception) {
			commonResponseDTO.setStatus(DevangamConstants.FAIL);
			commonResponseDTO.setMessage("Service is down. Please after some time.");
			log.error("Disable PatientDetails failed. HelpingHandId=" + helpingHandId, exception);
		}
		return commonResponseDTO;
	}
}


