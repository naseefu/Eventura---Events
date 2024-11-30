package com.eventura.utils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import com.eventura.DTO.EventDTO;
import com.eventura.DTO.UserDTO;
import com.eventura.entity.Event;
import com.eventura.entity.User;

public class Utils {

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

	public static UserDTO mapUserEntityToUserDTO(User savedUser) {
		if(savedUser!=null) {
			
			UserDTO userDTO = new UserDTO();
			userDTO.setId(savedUser.getId());
			userDTO.setDob(savedUser.getDob());
			userDTO.setEmail(savedUser.getEmail());
			userDTO.setFirstname(savedUser.getFirstname());
			userDTO.setMidname(savedUser.getMidname());
			userDTO.setLastname(savedUser.getLastname());
			userDTO.setProilepicture(savedUser.getProfilepicture());
			userDTO.setRole(savedUser.getRole());
			userDTO.setGender(savedUser.getGender());
			userDTO.setPhonenumber(savedUser.getPhoneNumber());
			return userDTO;
		}
		else {
			return null;
		}
	}

	public static List<EventDTO> mapPageEventEntityToPageEventDto(Page<Event> events) {
	    if (events == null) {
	        return Collections.emptyList();
	    }

	    return events.stream().map(e -> {
	        EventDTO dto = new EventDTO();
	        dto.setId(e.getId());
	        dto.setEventname(e.getEventname());
	        dto.setDescription(e.getDescription());
	        dto.setCategory(e.getCategory());
	        dto.setAbout(e.getAbout());
	        dto.setLocation(e.getLocation());
	        dto.setPrice(e.getPrice());
	        dto.setStartdate(e.getStartdate());
	        dto.setEnddate(e.getEnddate());
	        dto.setCanceled(e.isCanceled());
	        dto.setInitialprice(e.getInitialprice());
	        dto.setEventCode(e.getEventcode());
	        dto.setEventposter(e.getEventposter());
	        dto.setCapacity(e.getCapacity());
	        dto.setEventtype(e.getEventtype());
	        dto.setStartdate(e.getStartdate());
	        dto.setEnddate(e.getEnddate());
	        dto.setStarttime(e.getStarttime());
	        dto.setEndtime(e.getEndtime());
	        dto.setEventmethod(e.getEventmethod());
	        dto.setMessage(e.getMessage());
	        dto.setTags(e.getTag());
	        return dto;
	    }).collect(Collectors.toList());
	}
	public static List<EventDTO> mapListEventEntityToListEventDto(List<Event> events) {
	    if (events == null) {
	        return Collections.emptyList();
	    }

	    return events.stream().map(e -> {
	        EventDTO dto = new EventDTO();
	        dto.setId(e.getId());
	        dto.setEventname(e.getEventname());
	        dto.setDescription(e.getDescription());
	        dto.setCategory(e.getCategory());
	        dto.setAbout(e.getAbout());
	        dto.setLocation(e.getLocation());
	        dto.setPrice(e.getPrice());
	        dto.setStartdate(e.getStartdate());
	        dto.setEnddate(e.getEnddate());
	        dto.setCanceled(e.isCanceled());
	        dto.setInitialprice(e.getInitialprice());
	        dto.setEventCode(e.getEventcode());
	        dto.setEventposter(e.getEventposter());
	        dto.setCapacity(e.getCapacity());
	        dto.setEventtype(e.getEventtype());
	        dto.setStartdate(e.getStartdate());
	        dto.setEnddate(e.getEnddate());
	        dto.setStarttime(e.getStarttime());
	        dto.setEndtime(e.getEndtime());
	        dto.setEventmethod(e.getEventmethod());
	        dto.setMessage(e.getMessage());
	        dto.setHostedBy(e.getUser().getId());
	        dto.setTags(e.getTag());
	        return dto;
	    }).collect(Collectors.toList());
	}
	
	///////////////////////////////////////////////////////////

	public static EventDTO mapEventEntityToEventDTO(Event event) {
		
		if(event!=null) {
			
			EventDTO eventDTO = new EventDTO();
			eventDTO.setEventname(event.getEventname());
			eventDTO.setDescription(event.getDescription());
			eventDTO.setAbout(event.getAbout());
			eventDTO.setCategory(event.getCategory());
			eventDTO.setStartdate(event.getStartdate());
			eventDTO.setEnddate(event.getEnddate());
			eventDTO.setInitialprice(event.getInitialprice());
			eventDTO.setLocation(event.getLocation());
			eventDTO.setPrice(event.getPrice());
			eventDTO.setEventCode(event.getEventcode());
			eventDTO.setId(event.getId());
			eventDTO.setEventposter(event.getEventposter());
			eventDTO.setCapacity(event.getCapacity());
			eventDTO.setEventtype(event.getEventtype());
			eventDTO.setStartdate(event.getStartdate());
			eventDTO.setEnddate(event.getEnddate());
			eventDTO.setStarttime(event.getStarttime());
			eventDTO.setEndtime(event.getEndtime());
			eventDTO.setEventmethod(event.getEventmethod());
			eventDTO.setTags(event.getTag());
			eventDTO.setEventveriety(event.getEventveriety());
			eventDTO.setMessage(event.getMessage());
			eventDTO.setCanceled(event.isCanceled());
			eventDTO.setHostedBy(event.getUser().getId());
			return eventDTO;
			
		}
		else {
			return new EventDTO();
		}
		
	}
	
	/////////////////////////////////////////////////////////////////


}
