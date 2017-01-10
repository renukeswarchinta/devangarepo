package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.DonationDetailsDTO;
import com.devangam.dto.EducationDetailsDTO;
import com.devangam.dto.OldAgeHomeHelpingHandDTO;
import com.devangam.dto.PatientDetailsDTO;
import com.devangam.dto.UserCommentsDTO;
import com.devangam.entity.Education;
import com.devangam.entity.OldageHome;
import com.devangam.entity.Patients;
import com.devangam.service.DonationDetailsImpl;
import com.devangam.service.EducationHelpingHandImpl;
import com.devangam.service.OldAgeHomeHelpingHandImpl;
import com.devangam.service.PatientHelpingHandImpl;
import com.google.gson.Gson;

@RestController
public class HelpingHandController {
	
	@Autowired
	private EducationHelpingHandImpl educationHelpingHand; 
	
	@Autowired
	private PatientHelpingHandImpl patientHelpingHand;
	
	@Autowired
	private OldAgeHomeHelpingHandImpl oldAgeHomeHelpingHand;
	
	@Autowired
	private DonationDetailsImpl donationDetailsImpl;
	
	@RequestMapping(value="/api/addEducationDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO addEducationDetails(@RequestBody EducationDetailsDTO educationDetails){
		return educationHelpingHand.createEducationDetails(educationDetails);
	} 
	@RequestMapping(value ="/api/getEducationDetails",method=RequestMethod.GET)
	public @ResponseBody Gson getEducationDetailsDTO(){
		List<Education> listOfEducaitonDetails =  educationHelpingHand.getEducationDetails();
		Gson gson = new Gson();
		gson.toJson(listOfEducaitonDetails);
		return gson;
	}
	
	@RequestMapping(value="/api/addPatientDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO addPatientDetails(@RequestBody PatientDetailsDTO patientDetailsDTO){
		return patientHelpingHand.savePatientHelpingHandDetails(patientDetailsDTO);
	}
	
	@RequestMapping(value ="/api/getPatientDetails",method=RequestMethod.GET)
	public @ResponseBody Gson getPatientDetailsDTO(){
		List<Patients> listOfPatinetDetails =  patientHelpingHand.getPatientDetailsDTO();
		Gson gson = new Gson();
		gson.toJson(listOfPatinetDetails);
		return gson;
	}
	 
	

	@RequestMapping(value="/api/addOldAgeHomeDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO addOldAgeHomeDetails(@RequestBody OldAgeHomeHelpingHandDTO oldAgeHomeHelpingHandDTO){
		return oldAgeHomeHelpingHand.saveOldAgeHomeHelpingHandDetails(oldAgeHomeHelpingHandDTO);
	} 

	@RequestMapping(value ="/api/getOldAgeHomeDetails",method=RequestMethod.GET)
	public @ResponseBody Gson getOldAgeHomeDetailsDTO(){
		List<OldageHome> listOfOldAgeHomeDetails =  oldAgeHomeHelpingHand.getOldAgeHomeDetailsDTO();
		Gson gson = new Gson();
		gson.toJson(listOfOldAgeHomeDetails);
		return gson;
	}
	 
	@RequestMapping(value ="/api/saveDonationDetails",method=RequestMethod.GET)
	public @ResponseBody CommonResponseDTO saveDonationDetails(@RequestBody DonationDetailsDTO donationDetailsDTO){
		
		donationDetailsImpl.saveDonationDetails(donationDetailsDTO);
		return null;
		
	}
	// How do we send big comments and which helping hand he is asking for
	@RequestMapping(value ="/api/saveHelpingHandUserComments",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO saveHelpingHandUserComments(@ModelAttribute UserCommentsDTO userComments){
		
		return null;
		
	}
	

	
	
}
