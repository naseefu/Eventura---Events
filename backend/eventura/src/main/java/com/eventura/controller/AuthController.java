package com.eventura.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eventura.DTO.EditProfileDTO;
import com.eventura.DTO.LoginRequest;
import com.eventura.DTO.Response;
import com.eventura.entity.Contact;
import com.eventura.entity.User;
import com.eventura.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody User user,@RequestParam String confirmpass){
		System.out.println(user.getPhoneNumber());
		Response response = userServiceImpl.registerNormal(user,confirmpass);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest){
		Response response = userServiceImpl.login(loginRequest);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	@GetMapping("/feedback")
	public ResponseEntity<Response> getSomeFeedback(){
		
		Response response = userServiceImpl.getSomeFeedback();
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@PostMapping("/contact")
	public ResponseEntity<Response> contactUser(@RequestBody Contact contact){
		
		Response response = userServiceImpl.userContact(contact);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@PostMapping("/edit-profile")
	public ResponseEntity<Response> editprofile(@RequestBody EditProfileDTO editProfileDTO){
		
		Response response = userServiceImpl.editProfile(editProfileDTO);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@GetMapping("/get-user/{userId}")
	public ResponseEntity<Response> getUserDetails(@PathVariable Long userId){
		
		Response response = userServiceImpl.getUserDetails(userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@PostMapping("/otpverify/{userId}/{otp}")
	public ResponseEntity<Response> verifyOtp(@PathVariable Long userId,@PathVariable String otp){
		
		Response response = userServiceImpl.otpVerification(otp, userId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
}
