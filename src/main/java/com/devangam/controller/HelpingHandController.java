package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.DonationDetailsDTO;
import com.devangam.dto.EducationDetailsDTO;
import com.devangam.dto.HelpingHandDonationDetails;
import com.devangam.dto.JobOpportunityDTO;
import com.devangam.dto.OldAgeHomeHelpingHandDTO;
import com.devangam.dto.PatientDetailsDTO;
import com.devangam.dto.UserCommentsDTO;
import com.devangam.entity.Education;
import com.devangam.entity.JobOpportunities;
import com.devangam.entity.OldageHome;
import com.devangam.service.DonationDetailsImpl;
import com.devangam.service.EducationHelpingHandImpl;
import com.devangam.service.JobOpportunitiesServiceImpl;
import com.devangam.service.OldAgeHomeHelpingHandImpl;
import com.devangam.service.PatientHelpingHandImpl;
import com.devangam.service.UserCommentsService;
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
	
	@Autowired 
	private JobOpportunitiesServiceImpl jobOpportunitiesServiceImpl;
	@Autowired 
	private UserCommentsService userCommentsService;
	
	@RequestMapping(value="/api/addEducationDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO addEducationDetails(@RequestBody EducationDetailsDTO educationDetails){
		return educationHelpingHand.createEducationDetails(educationDetails);
	} 
	@RequestMapping(value ="/api/getEducationDetails",method=RequestMethod.GET)
	public @ResponseBody List<Education> getEducationDetailsDTO(){
		List<Education> listOfEducaitonDetails =  educationHelpingHand.getEducationDetails();
		return listOfEducaitonDetails;
	}
	@RequestMapping(value ="/api/disableEducationDetailsById",method=RequestMethod.DELETE)
	public @ResponseBody CommonResponseDTO disableEducationDetailsById(@RequestParam("helpingHandId") String helpingHandId){
		return educationHelpingHand.disableEducationDetailsById(helpingHandId);
	}
	@RequestMapping(value="/api/addPatientDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO addPatientDetails(@RequestBody PatientDetailsDTO patientDetailsDTO){
		return patientHelpingHand.savePatientHelpingHandDetails(patientDetailsDTO);
	}
	
	@RequestMapping(value ="/api/getPatientDetails",method=RequestMethod.GET)
	public @ResponseBody List<PatientDetailsDTO> getPatientDetailsDTO(){
		return  patientHelpingHand.getPatientDetailsDTO();
	}
	 
	@RequestMapping(value ="/api/disablePatientDetailsById",method=RequestMethod.DELETE)
	public @ResponseBody CommonResponseDTO disablePatientDetailsById(@RequestParam("helpingHandId") String helpingHandId){
		return patientHelpingHand.disablePatientDetailsById(helpingHandId);
	}
	

	@RequestMapping(value="/api/addOldAgeHomeDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO addOldAgeHomeDetails(@RequestBody OldAgeHomeHelpingHandDTO oldAgeHomeHelpingHandDTO){
		return oldAgeHomeHelpingHand.saveOldAgeHomeHelpingHandDetails(oldAgeHomeHelpingHandDTO);
	} 

	@RequestMapping(value ="/api/getOldAgeHomeDetails",method=RequestMethod.GET)
	public @ResponseBody List<OldageHome>  getOldAgeHomeDetailsDTO(){
		return oldAgeHomeHelpingHand.getOldAgeHomeDetailsDTO();
		
	}
	
	@RequestMapping(value ="/api/disableOldAgeHomeDetailsById",method=RequestMethod.DELETE)
	public @ResponseBody CommonResponseDTO disableOldAgeHomeDetailsById(@RequestParam("helpingHandId") String helpingHandId){
		return oldAgeHomeHelpingHand.disableOldAgeHomeDetailsById(helpingHandId);
	}
	
	
	@RequestMapping(value ="/api/saveDonationDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO saveDonationDetails(@RequestBody DonationDetailsDTO donationDetailsDTO){
		return donationDetailsImpl.saveDonationDetails(donationDetailsDTO);
		
	}
	
	@RequestMapping(value ="/api/getJobOpportunities",method=RequestMethod.GET)
	public @ResponseBody List<JobOpportunities> getJobOpportunities(){
		return jobOpportunitiesServiceImpl.getJobOpportunities();
		
	}
	
	@RequestMapping(value ="/api/saveJobDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO saveJobDetails(@RequestBody JobOpportunityDTO jobOpportunityDTO){
		return jobOpportunitiesServiceImpl.saveJobOpportunities(jobOpportunityDTO);
		
	}
	
	@RequestMapping(value ="/api/editEducationHelpingHands",method=RequestMethod.POST)
 	public @ResponseBody CommonResponseDTO editEducationHelpingHands(@RequestBody  EducationDetailsDTO educationDetailsDTO){
			return  educationHelpingHand.updateEducationHelpingHandDetails(educationDetailsDTO);
 	}
 	@RequestMapping(value ="/api/editOldAgeHomeHelpingHands",method=RequestMethod.POST)
 	public @ResponseBody CommonResponseDTO editOldAgeHomeHelpingHands(@RequestBody  OldAgeHomeHelpingHandDTO oldAgeHomeHelpingHandDTO){
			return  oldAgeHomeHelpingHand.updateOldAgeHomeDetails(oldAgeHomeHelpingHandDTO);
 		
 	}
 	@RequestMapping(value ="/api/editPatientHelpingHands",method=RequestMethod.POST)
 	public @ResponseBody CommonResponseDTO editPatientHelpingHands(@RequestBody  PatientDetailsDTO patientDetailsDTO){
			return patientHelpingHand.updatePatientDetails(patientDetailsDTO);
	}
	
	@RequestMapping(value ="/api/submitUserComments",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO submitUserComments(@RequestBody  UserCommentsDTO userCommentsDTO){
			return userCommentsService.submitUserComments(userCommentsDTO);
 	}
	// 
	@RequestMapping(value ="/api/getDonationDetailsByHelpingHandId",method=RequestMethod.GET)
	public @ResponseBody HelpingHandDonationDetails getDonationDetailsByHelpingHandId(@RequestParam String helpingHandId,
			@RequestParam String helpingHandType){
		HelpingHandDonationDetails donationDetails = donationDetailsImpl.getDonationDetailsById(helpingHandId,helpingHandType);
		System.out.println(donationDetails.getDonationAmount());
		return null;
	}
	
}
