package com.eventura.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventura.DTO.BookingDTO;
import com.eventura.DTO.EventDTO;
import com.eventura.DTO.Response2;
import com.eventura.DTO.UserDTO;
import com.eventura.entity.BookedMembers;
import com.eventura.entity.Event;
import com.eventura.entity.User;
import com.eventura.exception.OurException;
import com.eventura.repository.BookingRepository;
import com.eventura.repository.EventRepository;
import com.eventura.utils.Utils;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
//	@Autowired
//	private UserRepository userRepository;
	
	@Autowired
	private EventRepository eventRepository;

	public Response2 getBookingOfEachUser(Long userId) {
		
		Response2 response = new Response2();
		
		if(userId==null) {
			response.setStatusCode(400);
			response.setMessage("Invalid credentials");
			return response;
		}
		
		try {
			
			List<BookedMembers> bookings = bookingRepository.findAllByUserId(userId);
			
			List<EventDTO> eventList = new ArrayList<>();
			List<BookingDTO> bookingList = new ArrayList<>();
			
			for(BookedMembers b:bookings) {
				
				if(b.getBookingstatus().equals("Success")) {
				Event event = b.getEvent();

				EventDTO eventDto = Utils.mapEventEntityToEventDTO(event);
				BookingDTO bookingDTO = new BookingDTO();
				bookingDTO.setId(b.getId());
				bookingDTO.setBookingId(b.getBookingid());
				bookingDTO.setBookingdate(b.getBookingdate());
				bookingDTO.setBookingprice(b.getBookingprice());
				bookingDTO.setSeatsreserved(b.getSeatsreserved());
				
				eventList.add(eventDto);
				bookingList.add(bookingDTO);
				}
			}
			
			response.setEventList(eventList);
			response.setBookList(bookingList);
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
			response.setMessage("Error occured during Booking fetching" + e.getMessage());
		}
		return response;
		
	}

	public Response2 getEventTicket(Long userId, Long bookId) {
		
		Response2 response = new Response2();
		
		if(userId==null || bookId==null) {
			response.setStatusCode(400);
			response.setMessage("Invalid");
			return response;
		}
		
		try {
			
			BookedMembers bookedMembers = bookingRepository.findByIdAndUserId(bookId,userId).orElseThrow(()->new OurException("No Booking found"));
			
			Event event = bookedMembers.getEvent();
			
			EventDTO eventDTO = Utils.mapEventEntityToEventDTO(event);
			
			if(bookedMembers.getBookingstatus().equals("Success")) {
				
			
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setId(bookedMembers.getId());
			bookingDTO.setBookingId(bookedMembers.getBookingid());
			bookingDTO.setBookingdate(bookedMembers.getBookingdate());
			bookingDTO.setBookingprice(bookedMembers.getBookingprice());
			bookingDTO.setSeatsreserved(bookedMembers.getSeatsreserved());
			
			response.setEvent(eventDTO);
			response.setBookingDTO(bookingDTO);
			
			response.setStatusCode(200);
			response.setMessage("Successs");
			
			}
			
			else {
				response.setStatusCode(400);
				response.setMessage("Invalid");
			}
			
			return response;
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during Booking fetching" + e.getMessage());
		}
		return response;
	}
	
	
	public Response2 getAllBookingofEvent(Long userId,Long eventId) {
	
		Response2 response = new Response2();
		
		if(userId==null || eventId==null) {
			response.setStatusCode(404);
			response.setMessage("Not Found");
			return response;
		}
		
		try {
			
			if(eventRepository.existsByIdAndUserId(eventId,userId)) {
				
				List<BookedMembers> bookedMembers = bookingRepository.findAllByEventId(eventId);
				
				List<BookingDTO> bookingDTOs = new ArrayList<>();
				
				List<UserDTO> userDTOs = new ArrayList<>();
				
				Event event = eventRepository.findById(eventId).orElseThrow(()->new OurException("No Event"));
				
				EventDTO eventDto = Utils.mapEventEntityToEventDTO(event);
				
				LocalDateTime time = LocalDateTime.now();
				
				for(BookedMembers b:bookedMembers) {
					
					User user = b.getUser();
					
					UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
					
					BookingDTO bookingDTO = new BookingDTO();
					
					if(b.getBookingstatus().equals("Success")) {
							
						
					}
					else {
						
						long minutesDifference = Duration.between(b.getBookingdate(), time).toMinutes();	
						bookingDTO.setMinuteDuration(minutesDifference);
						
					}
					
					bookingDTO.setBookingdate(b.getBookingdate());
					bookingDTO.setBookingId(b.getBookingid());
					bookingDTO.setBookingprice(b.getBookingprice());
					bookingDTO.setStatus(b.getBookingstatus());
					bookingDTO.setSeatsreserved(b.getSeatsreserved());
					bookingDTO.setOrderId(b.getOrderId());
					
					bookingDTOs.add(bookingDTO);
					userDTOs.add(userDTO);
				}
				
				response.setEvent(eventDto);
				response.setBookList(bookingDTOs);
				response.setUserDTOs(userDTOs);
				
				response.setStatusCode(200);
				response.setMessage("Success");
				return response;
				
			}
			else {
				response.setStatusCode(400);
				response.setMessage("Event not found");
				return response;
			}
			
		}
		catch(OurException e) {
			
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
			
		}
		catch(Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setMessage("Error occured during fetching Booking details");
		}
		return response;
		
	}
	
}
