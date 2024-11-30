package com.eventura.serviceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eventura.DTO.Response2;
import com.eventura.entity.BookedMembers;
import com.eventura.entity.Event;
import com.eventura.entity.User;
import com.eventura.exception.OurException;
import com.eventura.repository.BookingRepository;
import com.eventura.repository.EventRepository;
import com.eventura.repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import jakarta.annotation.PostConstruct;

@Service
public class PaymentService {

    private RazorpayClient client;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public PaymentService() {
        
    }

    @PostConstruct
    public void init() throws Exception {
        this.client = new RazorpayClient(keyId, keySecret);
    }
    
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
    
    

    public Response2 createOrder(Long userId,Long eventId,String seatCount) throws Exception {
        Response2 response = new Response2();
        
        BookedMembers bookedMembers = new BookedMembers();
        
        if(userId==null || eventId==null) {
        	throw new OurException("An error occured");
        }
        
        Event event = eventRepository.findById(eventId)
                                     .orElseThrow(() -> new OurException("Event Not Exist"));

        User user = userRepository.findById(userId).orElseThrow(()->new OurException("User Not Exist"));
        
        int seats = Integer.parseInt(seatCount);
        LocalDateTime now = LocalDateTime.now();
        
        if(!event.getCapacity().equalsIgnoreCase("unlimited"))
        {
        	if(event.getCurrentseats()>=seats) {
        	BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(event.getPrice()));
            
            BigDecimal percentage = new BigDecimal("0.05");
            BigDecimal amountBigDecimal = bigDecimalValue.multiply(percentage).add(bigDecimalValue);
            
            Long amount = amountBigDecimal.setScale(0, RoundingMode.HALF_UP).longValue();
            
            JSONObject orderRequest = new JSONObject();
            
            orderRequest.put("amount", amount * seats * 100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");
            
            Order order = client.orders.create(orderRequest);
            
            Long currentseats = event.getCurrentseats()-Long.valueOf(seatCount);
            
            System.out.println(currentseats);
            
            eventRepository.updateEventCount(currentseats, eventId);
            
            bookedMembers.setOrderId(order.get("id").toString());
            bookedMembers.setUser(user);
            bookedMembers.setEvent(event);
            bookedMembers.setSeatsreserved(seats);
            bookedMembers.setBookingprice(event.getPrice());
            bookedMembers.setPaymentstatus("Pending");
            bookedMembers.setSeatsreserved(seats);
            bookedMembers.setBookingid(generatedRandomAlphanumeric(15));
            bookedMembers.setBookingstatus("Pending");
            bookedMembers.setBookingdate(now);
            
            bookingRepository.save(bookedMembers);
            
            response.setStatusCode(200);
            response.setMessage("Success");
            
            response.setOrderId(order.get("id"));
            response.setAmount(Long.valueOf(order.get("amount").toString()));
            response.setCurrency(order.get("currency"));
            
            return response;
        }
        else {
        	
        	response.setStatusCode(400);
        	response.setMessage("Sorry, we only have "+ event.getCurrentseats()+ " seats left. Please adjust your booking");
        	return response;
        	
        }}
        else {
        	BigDecimal bigDecimalValue = new BigDecimal(String.valueOf(event.getPrice()));
            
            BigDecimal percentage = new BigDecimal("0.05");
            BigDecimal amountBigDecimal = bigDecimalValue.multiply(percentage).add(bigDecimalValue);
            
            Long amount = amountBigDecimal.setScale(0, RoundingMode.HALF_UP).longValue();
            
            JSONObject orderRequest = new JSONObject();
            
            orderRequest.put("amount", amount * seats * 100);
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "order_rcptid_11");
            
            Order order = client.orders.create(orderRequest);
            
            bookedMembers.setOrderId(order.get("id").toString());
            bookedMembers.setUser(user);
            bookedMembers.setEvent(event);
            bookedMembers.setSeatsreserved(seats);
            bookedMembers.setBookingprice(event.getPrice());
            bookedMembers.setPaymentstatus("Pending");
            bookedMembers.setBookingstatus("Pending");
            bookedMembers.setSeatsreserved(seats);
            bookedMembers.setBookingid(generatedRandomAlphanumeric(15));
            bookedMembers.setBookingdate(now);
            
            bookingRepository.save(bookedMembers);
            
            response.setStatusCode(200);
            response.setMessage("Success");
            
            response.setOrderId(order.get("id"));
            response.setAmount(Long.valueOf(order.get("amount").toString()));
            response.setCurrency(order.get("currency"));
            
            return response;
        }
    }
    
    
    
    
    public Response2 finishOrder(Long userId,Long eventId,String orderId,String paymentId) {
    	
    	Response2 response = new Response2();
    	
    	try {
    		
    		User user = userRepository.findById(userId).orElseThrow(()->new OurException("User not found"));
    		
    		Event event = eventRepository.findById(eventId).orElseThrow(()->new OurException("User not found"));
    		
    		LocalDateTime currentTime = LocalDateTime.now();
    		
    		BookedMembers bookedMembers2 = bookingRepository.findByOrderIdAndUserIdAndEventId(orderId,userId,eventId);
    		
    		if(bookedMembers2!=null) {
    			
    			bookingRepository.updateEventCount(currentTime, "Success", "Success", paymentId, bookedMembers2.getId());
    			emailService.sendBookingConfirmation(user.getFirstname(), user.getLastname(), user.getEmail(),
    					bookedMembers2.getBookingid(), bookedMembers2.getSeatsreserved(), event.getEventname());
    			response.setStatusCode(200);
    			response.setMessage("Success");
    			return response;
    			
    		}
    		
    		else {
    			
    			response.setStatusCode(400);
    			response.setMessage("Such booking not exist");
    			return response;
    			
    		}
    		
    		
    	}
    	catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during Finish order " + e.getMessage());
		}
		return response;
    }

    
    public Response2 cancelOrder(Long userId,Long eventId,String orderId) {
    	
    	Response2 response = new Response2();
    	
    	
    	Event event = eventRepository.findById(eventId).orElseThrow(()->new OurException("User not found"));
    	
    	try {
    		
    		BookedMembers bookedMembers2 = bookingRepository.findByOrderIdAndUserIdAndEventId(orderId,userId,eventId);
    		
    		Long minutes = Duration.between(bookedMembers2.getBookingdate(), LocalDateTime.now()).toMinutes();
    		
    		if(!bookedMembers2.getBookingstatus().equals("Success") && minutes>20) {
    		
    			bookingRepository.deleteById(bookedMembers2.getId());
    		
    			Long seats = event.getCurrentseats()+Long.valueOf(bookedMembers2.getSeatsreserved().toString());
    		
    			eventRepository.updateEventCount(seats,eventId);
    			response.setStatusCode(200);
        		response.setMessage("Booking cancelled");
    		}
    		else {
    			
    			response.setStatusCode(400);
    			response.setMessage("Error occured during deletion");
    			return response;
    			
    		}
    		
    		return response;
    		
    		
    	}
    	catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during User Registration " + e.getMessage());
		}
		return response;
    	
    	
    }
    
}
