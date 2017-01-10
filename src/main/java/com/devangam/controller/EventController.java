package com.devangam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devangam.dto.EventDTO;
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
	public void saveEventDetails(EventDTO eventDTO){
		Events event = objectMapper.convertValue(eventDTO, Events.class);
		eventService.saveEventRepository(event);
	}
}
