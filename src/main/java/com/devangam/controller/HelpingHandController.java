package com.devangam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.EducationDetailsDTO;
import com.devangam.service.EducationHelpingHandImpl;
import com.devangam.service.IHelpingHandService;
import com.devangam.service.OldAgeHomeHelpingHandImpl;
import com.devangam.service.PatientHelpingHandImpl;

@RestController
public class HelpingHandController {
	
	@Autowired
	private EducationHelpingHandImpl helpingHand; 
	
	@Autowired
	private PatientHelpingHandImpl patientelpingHand;
	
	@Autowired
	private OldAgeHomeHelpingHandImpl oldAgeHomeHelpingHand;
	
	@RequestMapping(value="/api/addEducationDetails",method=RequestMethod.POST)
	public boolean addEducationDetails(@RequestBody EducationDetailsDTO educationDetails){
		
		helpingHand.createEducationDetails(educationDetails);
		return true;
	} 
	@RequestMapping(value ="/api/getEducationDetails",method=RequestMethod.GET)
	public @ResponseBody EducationDetailsDTO getEducationDetailsDTO(){
		return new EducationDetailsDTO();
	}
	
	
}
