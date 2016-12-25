package com.devangam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.service.RegistrationService;

@RestController
public class CommunityLeadersRestController {

	@Autowired
	private RegistrationService registrationService ;
	
	@RequestMapping(path="/api/addCommunityLeaderDetails")
	public @ResponseBody CommonResponseDTO saveCommunityLeadersDTO(@RequestBody CommunityLeadersDTO communityLeadersDTO){
		CommonResponseDTO response = new CommonResponseDTO();
		registrationService.saveCommunityLeaders(communityLeadersDTO);
		return response;
	} 
}
