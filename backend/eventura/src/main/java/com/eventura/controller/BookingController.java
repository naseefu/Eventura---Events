package com.eventura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventura.DTO.Response2;
import com.eventura.serviceImpl.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@GetMapping("/user-bookings/{userId}")
	public ResponseEntity<Response2> getUserBooking(@PathVariable Long userId){
		
		Response2 response = bookingService.getBookingOfEachUser(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@GetMapping("/event-ticket/{userId}/{bookId}")
	public ResponseEntity<Response2> getEventTicket(@PathVariable Long userId,@PathVariable Long bookId){
		
		Response2 response = bookingService.getEventTicket(userId,bookId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@GetMapping("/booked-members/{userId}/{eventId}")
	public ResponseEntity<Response2> getAllEventTicketBooks(@PathVariable Long userId,@PathVariable Long eventId){
		
		Response2 response = bookingService.getAllBookingofEvent(userId, eventId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
}
