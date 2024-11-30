package com.eventura.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventDTO {

	private Long id;
	
	private Long HostedBy;
	
	private String about;
	
	private String eventname;
	
	private String description;
	
	private String category;
	
	private String location;
	
	private BigDecimal initialprice;
	
	private BigDecimal price;
	
	private LocalDateTime startdate;
	
	private LocalDateTime enddate;
	
	private boolean canceled;
	
	private String eventCode;
	
	private String eventposter;
	
	private String capacity;
	
	private String eventtype;
	
	private String eventmethod;
	
	private LocalTime starttime;
	
	private LocalTime endtime;
	
	private String tags;
	
	private String eventveriety;
	
	private String message;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEventveriety() {
		return eventveriety;
	}

	public void setEventveriety(String eventveriety) {
		this.eventveriety = eventveriety;
	}
	

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getEventtype() {
		return eventtype;
	}

	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

	public String getEventmethod() {
		return eventmethod;
	}

	public void setEventmethod(String eventmethod) {
		this.eventmethod = eventmethod;
	}

	public LocalTime getStarttime() {
		return starttime;
	}

	public void setStarttime(LocalTime starttime) {
		this.starttime = starttime;
	}

	public LocalTime getEndtime() {
		return endtime;
	}

	public void setEndtime(LocalTime endtime) {
		this.endtime = endtime;
	}

	public String getEventposter() {
		return eventposter;
	}

	public void setEventposter(String eventposter) {
		this.eventposter = eventposter;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getInitialprice() {
		return initialprice;
	}

	public void setInitialprice(BigDecimal initialprice) {
		this.initialprice = initialprice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDateTime startdate) {
		this.startdate = startdate;
	}

	public LocalDateTime getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDateTime enddate) {
		this.enddate = enddate;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public Long getHostedBy() {
		return HostedBy;
	}

	public void setHostedBy(Long hostedBy) {
		HostedBy = hostedBy;
	}

	
	
	
	
}
