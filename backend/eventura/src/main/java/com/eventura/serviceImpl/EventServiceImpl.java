package com.eventura.serviceImpl;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.eventura.DTO.EventDTO;
import com.eventura.DTO.Response;
import com.eventura.entity.Event;
import com.eventura.entity.User;
import com.eventura.exception.OurException;
import com.eventura.repository.EventRepository;
import com.eventura.repository.UserRepository;
import com.eventura.utils.Utils;

@Service
public class EventServiceImpl {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generatedRandomAlphanumeric(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }
    
	
	public Response getAllEvents(int page,int size) {
		
		Response response = new Response();
		
		try {
			Pageable pageable = PageRequest.of(page, size,Sort.by(Sort.Direction.DESC,"id"));
			Page<Event> event = eventRepository.findAll(pageable);
			List<EventDTO> eventList =  Utils.mapPageEventEntityToPageEventDto(event);
			response.setEventList(eventList);
			response.setStatusCode(200);
			response.setMessage("Success");
			return response;
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event fetching " + e.getMessage());
		}
		return response;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	
	
	// Hosting an event
	
	public Response registerEvent(Long userId,Event event) {
		
		Response response = new Response();
		
		if(userId==null) {
			
			response.setStatusCode(400);
			response.setMessage("Invalid");
			return response;
			
		}
		try {
			
			if(event.getAbout()==null || event.getCategory()==null || event.getDescription()==null || event.getEventname()==null || event.getInitialprice()==null || event.getLocation()==null 
					|| event.getPrice()==null || event.getCapacity()==null 
					|| event.getEventtype()==null || event.getEventposter()==null || event.getEventmethod()==null || event.getMessage()==null) {
				
				response.setStatusCode(400);
				response.setMessage("Check all the fields");
				return response;
				
			}
			
			if(event.getEventtype().equals("Specific")) {
			
			if(event.getStartdate()==null || event.getEnddate()==null) {
					response.setStatusCode(400);
					response.setMessage("Check startdate and enddate");
					return response;
			}
				
			if(event.getStartdate().isAfter(event.getEnddate())) {
				response.setStatusCode(400);
				response.setMessage("Check startdate and enddate");
				return response;
			}
			}
			else {
				
				if(event.getStarttime()==null || event.getEndtime()==null) {
					response.setStatusCode(400);
					response.setMessage("Check start time and end time");
					return response;
				}
				
				if(event.getStarttime().isAfter(event.getEndtime())) {
					response.setStatusCode(400);
					response.setMessage("Check start time and end time");
					return response;
				}
			}
			
			if(userRepository.existsById(userId)) {
				
				User user = userRepository.findById(userId).orElseThrow(()->new OurException("User not exist"));
				if(user.getRole().equals("HOST")) {
					String eventCode = generatedRandomAlphanumeric(10);
					event.setEventcode(eventCode);
					event.setCanceled(false);
					event.setCurrentseats(Long.valueOf(event.getCapacity()));
					event.setUser(user);
					eventRepository.save(event);
					EventDTO eventDTO = Utils.mapEventEntityToEventDTO(event);
					response.setEventDTO(eventDTO);
					response.setStatusCode(200);
					response.setMessage("Success");
					return response;
					
				}
				else {
					response.setStatusCode(400);
					response.setMessage("You can't Register event");
					return response;
				}
				
			}
			else {
				response.setStatusCode(400);
				response.setMessage("User not exist");
				return response;
			}
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	public Response getEachEvent(Long eventId) {
		
		Response response = new Response();
		
		if(eventId==null) {
			
			response.setStatusCode(400);
			response.setMessage("Invalid");
			return response;
			
		}
		try {
			
			Event event = eventRepository.findById(eventId).orElseThrow(()->new OurException("No such event exist"));
			EventDTO eventDTO = Utils.mapEventEntityToEventDTO(event);
			response.setStatusCode(200);
			response.setEventDTO(eventDTO);
			response.setMessage("Success");
			return response;
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
		
	}


	public Response searchEvent(String eventInput, String locationInput) {
		
		Response response = new Response();
		
		if(eventInput.equals("")&&locationInput.equals("")) {
			
			response.setStatusCode(400);
			response.setMessage("Fill minimum one field");
			return response;
			
		}	
		
		try {
			
			List<Event> eventList = eventRepository.searchEvents(eventInput, locationInput);
			List<EventDTO> event = Utils.mapListEventEntityToListEventDto(eventList);
			response.setEventList(event);
			response.setStatusCode(200);
			response.setMessage("success");
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
	}


	public Response getSomeEvent(Long eventNum) {
		Response response = new Response();
		if(eventNum<=0) {
			response.setStatusCode(400);
			response.setMessage("Failed");
			return response;
		}
		try {
			
			List<Event> event = eventRepository.findSomeEvent(eventNum);
		 	List<EventDTO> eventDTO = Utils.mapListEventEntityToListEventDto(event);
		 	response.setEventList(eventDTO);
		 	response.setStatusCode(200);
		 	response.setMessage("Success");
		 	return response;
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
	}


	public Response searchRecommendEvent(String eventInput,Long eventId) {
		
		Response response = new Response();
		
		if(eventInput.equals("")) {
			
			response.setStatusCode(400);
			response.setMessage("Invalid");
			return response;
			
		}	
		
		try {
			
			List<Event> eventList = eventRepository.searchNextRecEvents(eventInput,eventId);
			if(eventList.size()<4) {
				eventList.addAll(eventRepository.searchPrevRecEvents(eventInput, eventId));
			}
			List<EventDTO> event = Utils.mapListEventEntityToListEventDto(eventList);
			response.setEventList(event);
			response.setStatusCode(200);
			response.setMessage("success");
		}
		
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
		
	}

	

	public Response getHostedEvent(Long userId) {
		
		Response response = new Response();
		
		if(userId==null) {
			response.setStatusCode(404);
			response.setMessage("Invalid");
			return response;
		}
		
		try {
			User user = userRepository.findById(userId).orElseThrow(()->new OurException("no"));
			List<Event> event = user.getEvent();
//			List<Event> event = eventRepository.findByUserId(userId);
			response.setEventList(Utils.mapListEventEntityToListEventDto(event));
			response.setStatusCode(200);
			response.setMessage("success");
			
			return response;
			
		}
		
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
	}


	public Response hostEvents(Event event) {
		
		Response response = new Response();
		
		try {
			
			
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during event registration " + e.getMessage());
		}
		return response;
		
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	
}
