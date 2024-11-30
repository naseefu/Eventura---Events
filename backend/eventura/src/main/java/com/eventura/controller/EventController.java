package com.eventura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventura.DTO.Response;
import com.eventura.entity.Event;
import com.eventura.serviceImpl.EventServiceImpl;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventServiceImpl eventServiceImpl;
	
	@GetMapping("/getall-events/{page}/{size}")
	public ResponseEntity<Response> getAllEvents(@PathVariable int page,@PathVariable int size) {
		
		Response response = eventServiceImpl.getAllEvents(page, size);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@PostMapping("/register-event/{userId}")
	public ResponseEntity<Response> registerEvent(@PathVariable Long userId,@RequestBody Event event){
		
		Response response = eventServiceImpl.registerEvent(userId, event);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@GetMapping("/geteach-event/{eventId}")
	public ResponseEntity<Response> getEachEvent(@PathVariable Long eventId){
		
		Response response = eventServiceImpl.getEachEvent(eventId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/search-event")
	public ResponseEntity<Response> searchEvents(
	    @RequestParam(required = false) String eventInput,
	    @RequestParam(required = false) String locationInput) {

	    Response response = eventServiceImpl.searchEvent(eventInput, locationInput);
	    return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	@GetMapping("/recsearch-event")
	public ResponseEntity<Response> RecoSearchEvents(
	    @RequestParam(required = false) String eventInput,@RequestParam(required = false) Long eventId) {

	    Response response = eventServiceImpl.searchRecommendEvent(eventInput,eventId);
	    return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/getsome-event/{eventNum}")
	public ResponseEntity<Response> getSomeEvent(@PathVariable Long eventNum){
		Response response = eventServiceImpl.getSomeEvent(eventNum);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/getHostedEvents/{userId}")
	public ResponseEntity<Response> getHostedEvent(@PathVariable Long userId){
		Response response = eventServiceImpl.getHostedEvent(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	@PostMapping("/host-event/{userId}")
	public ResponseEntity<Response> hostEvents(@PathVariable Long userId,@RequestBody Event event) {
		
		Response response = eventServiceImpl.registerEvent(userId,event);
		
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
}
