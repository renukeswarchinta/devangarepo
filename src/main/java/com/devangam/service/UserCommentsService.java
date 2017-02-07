package com.devangam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.UserCommentsDTO;
import com.devangam.entity.OldageHome;
import com.devangam.entity.UserComments;
import com.devangam.repository.UserCommentsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UserCommentsService {
	
	@Autowired
	private UserCommentsRepository userCommentsRepository;
	@Autowired
	private ObjectMapper objectMapper;
	
	
	public CommonResponseDTO submitUserComments(UserCommentsDTO userCommentsDTO) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		UserComments userComments = objectMapper.convertValue(userCommentsDTO, UserComments.class);
		try{
			userCommentsRepository.save(userComments);
			commonResponseDTO.setMessage("USer Commetns saved successfully");
			commonResponseDTO.setStatus("200");
		}catch(Exception exception){
			commonResponseDTO.setMessage("Failed to save User Commetns");
			commonResponseDTO.setStatus("404");
		}
		return commonResponseDTO;
	}

	

	
}