package com.eventura.DTO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

	private int statusCode;

	private String message;

	private String token;

	private String role;

	private String expirationTime;
	
	private BookingDTO bookingDTO;
	
	private EventDTO eventDTO;
	
	private Page<EventDTO> eventPage;
	
	private FeedbackDTO feedbackDTO;
	
	private NotificationDTO notificationDTO;
	
	private ReminderDTO reminderDTO;
	
	private UserDTO userDTO;
	
	private List<BookingDTO> bookingList = new ArrayList<>();
	
	private List<EventDTO> eventList = new ArrayList<>();
	
	private List<FeedbackDTO> feedbackList = new ArrayList<>();
	
	private List<NotificationDTO> notificationList = new ArrayList<>();
	
	private List<ReminderDTO> reminderList = new ArrayList<>();
	
	private List<UserDTO> userList = new ArrayList<>();
	
	private WebsiteFeedbackDTO websiteFeedbackDTO;
	
	private List<WebsiteFeedbackDTO> websiteFeedbackDTOs = new ArrayList<>();

	public WebsiteFeedbackDTO getWebsiteFeedbackDTO() {
		return websiteFeedbackDTO;
	}

	public void setWebsiteFeedbackDTO(WebsiteFeedbackDTO websiteFeedbackDTO) {
		this.websiteFeedbackDTO = websiteFeedbackDTO;
	}

	public List<WebsiteFeedbackDTO> getWebsiteFeedbackDTOs() {
		return websiteFeedbackDTOs;
	}

	public void setWebsiteFeedbackDTOs(List<WebsiteFeedbackDTO> websiteFeedbackDTOs) {
		this.websiteFeedbackDTOs = websiteFeedbackDTOs;
	}

	public Page<EventDTO> getEventPage() {
		return eventPage;
	}

	public void setEventPage(Page<EventDTO> eventPage) {
		this.eventPage = eventPage;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(String expirationTime) {
		this.expirationTime = expirationTime;
	}

	public BookingDTO getBookingDTO() {
		return bookingDTO;
	}

	public void setBookingDTO(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}

	public EventDTO getEventDTO() {
		return eventDTO;
	}

	public void setEventDTO(EventDTO eventDTO) {
		this.eventDTO = eventDTO;
	}

	public FeedbackDTO getFeedbackDTO() {
		return feedbackDTO;
	}

	public void setFeedbackDTO(FeedbackDTO feedbackDTO) {
		this.feedbackDTO = feedbackDTO;
	}

	public NotificationDTO getNotificationDTO() {
		return notificationDTO;
	}

	public void setNotificationDTO(NotificationDTO notificationDTO) {
		this.notificationDTO = notificationDTO;
	}

	public ReminderDTO getReminderDTO() {
		return reminderDTO;
	}

	public void setReminderDTO(ReminderDTO reminderDTO) {
		this.reminderDTO = reminderDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<BookingDTO> getBookingList() {
		return bookingList;
	}

	public void setBookingList(List<BookingDTO> bookingList) {
		this.bookingList = bookingList;
	}

	public List<EventDTO> getEventList() {
		return eventList;
	}

	public void setEventList(List<EventDTO> eventList) {
		this.eventList = eventList;
	}

	public List<FeedbackDTO> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackDTO> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public List<NotificationDTO> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<NotificationDTO> notificationList) {
		this.notificationList = notificationList;
	}

	public List<ReminderDTO> getReminderList() {
		return reminderList;
	}

	public void setReminderList(List<ReminderDTO> reminderList) {
		this.reminderList = reminderList;
	}

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}
	
}
