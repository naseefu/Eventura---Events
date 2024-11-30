package com.eventura.DTO;

import java.time.LocalDateTime;

public class ReminderDTO {

	private Long id;
	
	private LocalDateTime remindeddate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getRemindeddate() {
		return remindeddate;
	}

	public void setRemindeddate(LocalDateTime remindeddate) {
		this.remindeddate = remindeddate;
	}
	
	
}
