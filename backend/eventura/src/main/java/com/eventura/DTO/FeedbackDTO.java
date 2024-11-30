package com.eventura.DTO;

import java.time.LocalDateTime;

public class FeedbackDTO {

	private Long id;
	
	private Float rating;
	
	private String review;
	
	private LocalDateTime reiewdate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public LocalDateTime getReiewdate() {
		return reiewdate;
	}

	public void setReiewdate(LocalDateTime reiewdate) {
		this.reiewdate = reiewdate;
	}
	
	
	
}
