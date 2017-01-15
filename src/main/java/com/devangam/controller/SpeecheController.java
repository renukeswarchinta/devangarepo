package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.entity.Speeches;
import com.devangam.service.SpeecheService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping
public class SpeecheController {

	@Autowired
	private SpeecheService speechService;
	@Autowired 
	private ObjectMapper objectMapper;
	
	@RequestMapping(value="/api/getAllSpeeches")
	public @ResponseBody List<Speeches> getAllSpeeches(){
		return speechService.getListOfSpeeches();
	}
	@RequestMapping(value="/api/admin/saveSpeeches")
	public @ResponseBody CommonResponseDTO saveSpeeches(@RequestBody Speeches speeche){
		Speeches speecheEntity = objectMapper.convertValue(speeche, Speeches.class);
		return speechService.saveSpeecheDetails(speecheEntity);
	}
	
	
}
