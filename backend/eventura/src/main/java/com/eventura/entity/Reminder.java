package com.eventura.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reminder")
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "event_id")
	private Event event;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	private User user;
	
	private LocalDateTime remindeddate;
	
	private String reminderstatus;
	
	private String remindertype; // email or SMS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getRemindeddate() {
		return remindeddate;
	}

	public void setRemindeddate(LocalDateTime remindeddate) {
		this.remindeddate = remindeddate;
	}

	public String getReminderstatus() {
		return reminderstatus;
	}

	public void setReminderstatus(String reminderstatus) {
		this.reminderstatus = reminderstatus;
	}

	public String getRemindertype() {
		return remindertype;
	}

	public void setRemindertype(String remindertype) {
		this.remindertype = remindertype;
	}
	
	
}
