package com.devangam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.service.RegistrationService;

@RestController
public class CommunityLeadersRestController {

	@Autowired
	private RegistrationService registrationService ;
	
	@RequestMapping(path="/api/addCommunityLeaderDetails")
	public @ResponseBody CommonResponseDTO saveCommunityLeadersDTO(
			@RequestParam(value = "files", required = false) MultipartFile file, 
			@RequestParam(value = "communityLeadersRequestJson", required = true) String communityLeadersRequestJson){
		
		CommunityLeadersDTO communityLeadersDTO = new CommunityLeadersDTO();
		communityLeadersDTO.setCommunityLeadersRequestJson(communityLeadersRequestJson);
		communityLeadersDTO.setMultipartFiles(file);
		return registrationService.saveCommunityLeaders(communityLeadersDTO);
	} 
}
