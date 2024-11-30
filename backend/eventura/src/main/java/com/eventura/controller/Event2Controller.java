//package com.eventura.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.eventura.DTO.Response;
//import com.eventura.entity.Event;
//import com.eventura.serviceImpl.EventServiceImpl;
//
//@RestController
//@RequestMapping("/event")
//public class Event2Controller {
//
//	
//	@Autowired
//	private EventServiceImpl eventServiceImpl;
//	
//	@PostMapping("/host-event/{userId}")
//	public ResponseEntity<Response> hostEvents(@PathVariable Long userId,@RequestBody Event event) {
//		
//		Response response = eventServiceImpl.registerEvent(userId,event);
//		
//		return ResponseEntity.status(response.getStatusCode()).body(response);
//		
//		
//	}
//	
//	
//}
