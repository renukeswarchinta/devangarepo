package com.devangam.service;

import static com.devangam.constants.DevangamConstants.FAIL;
import static com.devangam.constants.DevangamConstants.SUCCESS;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devangam.dto.CommonResponseDTO;
import com.devangam.dto.EventDTO;
import com.devangam.entity.Events;
import com.devangam.repository.EventRepository;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository eventRepository;

	public void saveEventRepository(Events event) {
		eventRepository.save(event);
	}

	public List<Events> getListOfEvents() {
		return eventRepository.findAllActiveEvents();
	}

	public CommonResponseDTO updateEvents(EventDTO eventDTO) {

		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			Events event = eventRepository.findOne(eventDTO.getId());
			if (null != event) {
				event.setEventDescription(eventDTO.getEventDescription());
				event.setEventLaunchDate(eventDTO.getEventLaunchDate());
				event.setEventName(eventDTO.getEventName());
				event.setEventPostDate(eventDTO.getEventPostDate());
				event.setEventPostEndDate(eventDTO.getEventPostEndDate());
				event.setHostedBy(eventDTO.getHostedBy());
				eventRepository.save(event);
				commonResponseDTO.setMessage("Success");
				commonResponseDTO.setStatus(SUCCESS);
			} else {
				commonResponseDTO.setMessage("Event not found. EventId=" + eventDTO.getId());
				commonResponseDTO.setStatus(FAIL);
			}

		} catch (Exception e) {
			commonResponseDTO.setMessage("Failed to update ");
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;

	}

	public CommonResponseDTO disableEventById(String id, int disable) {
		CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
		try {
			
			Events event= eventRepository.findOne(Long.valueOf(id));
			if(event == null){
				commonResponseDTO.setMessage("Entity with that id does not exists ");
				commonResponseDTO.setStatus(FAIL);
				return commonResponseDTO;
			}
			eventRepository.disableEvents(Long.valueOf(id));
			commonResponseDTO.setMessage("Successfully deleted");
			commonResponseDTO.setStatus(SUCCESS);
		} catch (Exception e) {
			commonResponseDTO.setMessage("Failed to update ");
			commonResponseDTO.setStatus(FAIL);
		}
		return commonResponseDTO;
	}
}
