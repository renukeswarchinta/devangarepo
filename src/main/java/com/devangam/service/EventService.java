package com.devangam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devangam.entity.Events;
import com.devangam.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository eventRepository;
	
	
	public void saveEventRepository(Events event){
		eventRepository.save(event);
	}
	public List<Events> getListOfEvents(){
		return eventRepository.findAll();
	}
}
