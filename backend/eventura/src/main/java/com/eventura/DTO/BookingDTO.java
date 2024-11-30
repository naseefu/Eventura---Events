package com.eventura.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BookingDTO {

	private Long id;
	
	private LocalDateTime bookingdate;
	
	private BigDecimal bookingprice;
	
	private String status;
	
	private BigDecimal discount;
	
	private String paymentstatus;
	
	private Integer seatsreserved;
	
	private String bookingId;
	
	private Long minuteDuration;
	
	private String orderId;
	
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getMinuteDuration() {
		return minuteDuration;
	}

	public void setMinuteDuration(Long minuteDuration) {
		this.minuteDuration = minuteDuration;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getBookingdate() {
		return bookingdate;
	}

	public void setBookingdate(LocalDateTime bookingdate) {
		this.bookingdate = bookingdate;
	}

	public BigDecimal getBookingprice() {
		return bookingprice;
	}

	public void setBookingprice(BigDecimal bookingprice) {
		this.bookingprice = bookingprice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getPaymentstatus() {
		return paymentstatus;
	}

	public void setPaymentstatus(String paymentstatus) {
		this.paymentstatus = paymentstatus;
	}

	public Integer getSeatsreserved() {
		return seatsreserved;
	}

	public void setSeatsreserved(Integer seatsreserved) {
		this.seatsreserved = seatsreserved;
	}
	
	
	
}
