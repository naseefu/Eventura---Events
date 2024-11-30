package com.eventura.DTO;

import java.util.List;

public class Response2 {

	private String orderId;
	
	private String currency;
	
	private Long amount;
	
	private int statusCode;

	private String message; 
	
	private EventDTO event;
	
	private List<EventDTO> eventList;
	
	private BookingDTO bookingDTO;
	
	private List<BookingDTO> bookList;
	
	private Long minuteDuration;
	
	private UserDTO userDTO;
	
	private List<UserDTO> userDTOs;	

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<UserDTO> getUserDTOs() {
		return userDTOs;
	}

	public void setUserDTOs(List<UserDTO> userDTOs) {
		this.userDTOs = userDTOs;
	}

	public Long getMinuteDuration() {
		return minuteDuration;
	}

	public void setMinuteDuration(Long minuteDuration) {
		this.minuteDuration = minuteDuration;
	}

	public EventDTO getEvent() {
		return event;
	}

	public void setEvent(EventDTO event) {
		this.event = event;
	}

	public List<EventDTO> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDTO> eventList) {
		this.eventList = eventList;
	}

	public BookingDTO getBookingDTO() {
		return bookingDTO;
	}

	public void setBookingDTO(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}

	public List<BookingDTO> getBookList() {
		return bookList;
	}

	public void setBookList(List<BookingDTO> bookList) {
		this.bookList = bookList;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
}
