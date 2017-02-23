package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.CommunityLeadersDTO;
import com.devangam.dto.EducationDetailsDTO;
import com.devangam.entity.CommunityLeader;
import com.devangam.service.CommunityLeaderService;
import com.devangam.service.RegistrationService;

@RestController
public class CommunityLeadersRestController {

	@Autowired
	private RegistrationService registrationService ;
	@Autowired 
	private CommunityLeaderService communityLeaderService;
	
	@RequestMapping(path="/api/addCommunityLeaderDetails")
	public @ResponseBody CommonResponseDTO saveCommunityLeadersDTO(
			@RequestParam(value = "file", required = false) MultipartFile file, 
			@RequestParam(value = "requestJson", required = true) String requestJson){
		
		CommunityLeadersDTO communityLeadersDTO = new CommunityLeadersDTO();
		communityLeadersDTO.setCommunityLeadersRequestJson(requestJson);
		communityLeadersDTO.setMultipartFiles(file);
		return registrationService.saveCommunityLeaders(communityLeadersDTO);
	} 
	
	@RequestMapping(path="/api/getCommunityLeaderDetails")
	public @ResponseBody List<CommunityLeader> getCommunityLeaders(){
		return communityLeaderService.getCommunityLeaders();
	} 
	
	
	
	@RequestMapping(value ="/api/updateCommunityLeaderDetails",method=RequestMethod.POST)
 	public @ResponseBody CommonResponseDTO updateCommunityLeaderDetails(@RequestBody  CommunityLeadersDTO communityLeadersDTO){
			return  communityLeaderService.updateCommunityLeaderDetails(communityLeadersDTO);
 	}
	@RequestMapping(value ="/api/deleteCommunityLeaderDetails",method=RequestMethod.DELETE)
 	public @ResponseBody CommonResponseDTO deleteCommunityLeaderDetails(@RequestParam("id") String id){
			return  communityLeaderService.deleteCommunityLeaderDetails(id);
 	}
}
