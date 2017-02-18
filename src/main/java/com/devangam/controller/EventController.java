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
import com.devangam.dto.EventDTO;
import com.devangam.dto.OldAgeHomeHelpingHandDTO;
import com.devangam.entity.Events;
import com.devangam.service.EventService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EventController {

	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value ="/api/getListOfEvents",method=RequestMethod.GET)
	public List<Events> getListOfEvents(){
		return eventService.getListOfEvents();
	}
	@RequestMapping(value ="/api/saveEventDetails",method=RequestMethod.POST)
	public @ResponseBody CommonResponseDTO saveEventDetails(@RequestBody EventDTO eventDTO){
		CommonResponseDTO common = new CommonResponseDTO();
		try{
			Events event = objectMapper.convertValue(eventDTO, Events.class);
			eventService.saveEventRepository(event);
			common.setMessage("Events Saved Succefully");
			common.setStatus("Success");
		}catch(Exception e){
			common.setMessage("Failed to save Events " );
			common.setStatus("Failed");
		}
		return common;
		
	}
	
	@RequestMapping(value ="/api/updateEvents",method=RequestMethod.POST)
 	public @ResponseBody CommonResponseDTO updateEvents(@RequestBody  EventDTO eventDTO){
			return  eventService.updateEvents(eventDTO);
 		
 	}
	
	@RequestMapping(value ="/api/disableEventById",method=RequestMethod.GET)
	public @ResponseBody CommonResponseDTO disableEventById(@RequestParam("id") String id){
		return eventService.disableEventById(id,0);
	}
}
