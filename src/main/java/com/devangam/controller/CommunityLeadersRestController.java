package com.devangam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.dto.ResponseDTO;
import com.devangam.service.RegistrationService;

@RestController
public class CommunityLeadersRestController {

	@Autowired
	private RegistrationService registrationService ;
	
	@RequestMapping(name="api/addCommunityLeaderDetails")
	public @ResponseBody ResponseDTO saveCommunityLeadersDTO(@RequestBody CommunityLeadersDTO communityLeadersDTO){
		ResponseDTO response = new ResponseDTO();
		registrationService.saveCommunityLeaders(communityLeadersDTO);
		return response;
	} 
}
