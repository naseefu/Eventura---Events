package com.eventura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventura.DTO.Response2;
import com.eventura.serviceImpl.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create-order/{userId}/{eventId}/{seatCount}")
    public ResponseEntity<Response2> createOrder(@PathVariable Long eventId,@PathVariable Long userId,@PathVariable String seatCount) throws Exception {
        
    	Response2 response = paymentService.createOrder(userId,eventId,seatCount);
    	return ResponseEntity.status(response.getStatusCode()).body(response);
    	
    }
    
    @PostMapping("/finish-order/{userId}/{eventId}/{orderId}/{paymentId}")
    public ResponseEntity<Response2> finishOrder(@PathVariable Long eventId,@PathVariable Long userId,@PathVariable String orderId,@PathVariable String paymentId){
        
    	Response2 response = paymentService.finishOrder(userId, eventId, orderId, paymentId);
    	return ResponseEntity.status(response.getStatusCode()).body(response);
    	
    }
    @PostMapping("/cancel-order/{userId}/{eventId}/{orderId}")
    public ResponseEntity<Response2> cancelOrder(@PathVariable Long eventId,@PathVariable Long userId,@PathVariable String orderId){
        
    	Response2 response = paymentService.cancelOrder(userId, eventId, orderId);
    	return ResponseEntity.status(response.getStatusCode()).body(response);
    	
    }
}
