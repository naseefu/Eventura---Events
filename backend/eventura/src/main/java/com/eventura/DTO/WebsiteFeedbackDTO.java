package com.eventura.DTO;

import java.math.BigDecimal;

public class WebsiteFeedbackDTO {

	private Long id;
	
	private String feedback;
	
	private BigDecimal star;
	
	private String firstname;
	
	private String lastname;
	
	private String profilepicture;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getProfilepicture() {
		return profilepicture;
	}

	public void setProfilepicture(String profilepicture) {
		this.profilepicture = profilepicture;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public BigDecimal getStar() {
		return star;
	}

	public void setStar(BigDecimal star) {
		this.star = star;
	}
	
	
	
}
