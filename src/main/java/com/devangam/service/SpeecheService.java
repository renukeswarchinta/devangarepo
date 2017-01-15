package com.devangam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.entity.Speeches;
import com.devangam.repository.SpeecheRepository;

@Service
public class SpeecheService {
	
	@Autowired
	private SpeecheRepository speechRepository;

	public List<Speeches> getListOfSpeeches() {
		return speechRepository.findAll();
	}
	
	public CommonResponseDTO saveSpeecheDetails(Speeches speech){
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try{
			speechRepository.save(speech);
			commonResponseDTO.setMessage("Speeches saved successfully");;
			commonResponseDTO.setStatus("Success");
		}catch(Exception e){
			commonResponseDTO.setStatus("Failed");
		}
		return commonResponseDTO;
	}

	
}
