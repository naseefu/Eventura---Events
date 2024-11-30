package com.eventura.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String eventname;

	private String category;

	private String location;
	
	private BigDecimal initialprice;

	private BigDecimal price;

	private LocalDateTime startdate;

	private LocalDateTime enddate;
	
	private String capacity;
	
	private String eventtype;
	
	private String eventmethod;
	
	private LocalTime starttime;
	
	private LocalTime endtime;

	@Lob 
	private String about;

	@Lob
	private String description;

	private boolean canceled;
	
	private String eventcode;
	
	@Lob
	private String eventposter;
	
	private String tag;
	
	private String eventveriety;
	
	private String message;
	
	private Long currentseats;
	
	


	@ManyToOne(fetch = FetchType.EAGER )
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<BookedMembers> bookingmembers = new ArrayList<>();

	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Reminder> reminder = new ArrayList<>();
	
	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Notification> notification = new ArrayList<>();
	
	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Attachment> attachment = new ArrayList<>();
	
	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Feedback> feedback = new ArrayList<>();
	
	@OneToMany(mappedBy = "event",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Payment> payment = new ArrayList<>();
	
	
	public Long getCurrentseats() {
		return currentseats;
	}

	public void setCurrentseats(Long currentseats) {
		this.currentseats = currentseats;
	}

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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public String getEventposter() {
		return eventposter;
	}

	public void setEventposter(String eventposter) {
		this.eventposter = eventposter;
	}

	public String getEventcode() {
		return eventcode;
	}

	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}
	
	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	public List<Feedback> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<Feedback> feedback) {
		this.feedback = feedback;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	public BigDecimal getInitialprice() {
		return initialprice;
	}

	public void setInitialprice(BigDecimal initialprice) {
		this.initialprice = initialprice;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BookedMembers> getBookingmembers() {
		return bookingmembers;
	}

	public void setBookingmembers(List<BookedMembers> bookingmembers) {
		this.bookingmembers = bookingmembers;
	}

	public List<Reminder> getReminder() {
		return reminder;
	}

	public void setReminder(List<Reminder> reminder) {
		this.reminder = reminder;
	}

	public List<Notification> getNotification() {
		return notification;
	}

	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public String getStatus() {
		if (canceled) {
			return "canceled";
		}
		LocalDateTime now = LocalDateTime.now();
		if (now.isBefore(startdate)) {
			return "upcoming";
		} else if (now.isAfter(enddate)) {
			return "expired";
		} else {
			return "ongoing";
		}
	}

}
