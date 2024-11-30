package com.eventura.serviceImpl;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventura.DTO.EditProfileDTO;
import com.eventura.DTO.LoginRequest;
import com.eventura.DTO.Response;
import com.eventura.DTO.UserDTO;
import com.eventura.DTO.WebsiteFeedbackDTO;
import com.eventura.entity.Contact;
import com.eventura.entity.User;
import com.eventura.entity.WebsiteFeedback;
import com.eventura.exception.OurException;
import com.eventura.repository.ContactRepository;
import com.eventura.repository.FeedbackRepository;
import com.eventura.repository.UserRepository;
import com.eventura.utils.JWTUtils;
import com.eventura.utils.Utils;


@Service
public class UserServiceImpl {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	@Autowired
	private EmailService emailService;
	
	
	private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generatedRandomAlphanumeric(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar);
        }
        return stringBuilder.toString();
    }

	
	
	
	public Response registerNormal(User user,String confirmpass) {
		Response response = new Response();
		try {
			
			if(user.getRole()==null || user.getRole().isBlank()) {
				throw new OurException("Please Specify what your looking for");
			}
			
			if(user.getFirstname()==null || user.getEmail()==null || user.getDob()==null || user.getPassword()==null || user.getLastname()==null ) {
				throw new OurException("Please Fill all the fields");
			}
			
			if(user.getProfilepicture().equals("")) {
				throw new OurException("Please choose an avatar");
			}
			
			if(!user.isIsaccept()) {
				throw new OurException("Please accept Terms of Services");
			}
			
			if(user.getGender()==null) {
				
				throw new OurException("Please specify gender");
				
			}
			
			if(user.getPhoneNumber()==null) {
				throw new OurException("Please Fill Phone number");
			}
			
			if(userRepository.existsByEmail(user.getEmail())) {
				throw new OurException("User Already exist");
			}
			
			if(!user.getPassword().equals(confirmpass)) {
				throw new OurException("Password doesn't match");
			}
			
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setVerified(false);
			String otp = generatedRandomAlphanumeric(6);
			user.setOtp(otp);
			
			emailService.sendOTP(user.getFirstname(), user.getLastname(), user.getEmail(), otp);
			
			userRepository.save(user);
			response.setUserDTO(Utils.mapUserEntityToUserDTO(user));
			response.setStatusCode(200);
			response.setMessage("success");
			
		}catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during User Registration " + e.getMessage());
		}
		return response;
		
	}
	
	
	
	
	
	public Response otpVerification(String otp,Long userId) {
		
		Response response = new Response();
		
		if(userId==null) {
			response.setStatusCode(400);
			response.setMessage("Failed to verify otp");
			return response;
		}
		
		if(otp==null) {
			response.setStatusCode(400);
			response.setMessage("Fill OTP");
			return response;
		}
		try {
			
			
			
			User user = userRepository.findById(userId).orElseThrow(()->new OurException("User not found"));
			
			
			if(user.isVerified()) {
				response.setStatusCode(200);
				response.setMessage("User already verified");
				return response;
			}
			
			
			if(otp.equals(user.getOtp())) {
				
				userRepository.updateVerification(true, "", userId);
				response.setStatusCode(200);
				response.setMessage("Success");
				try {
	                emailService.sendEmailOnRegister(user.getFirstname(), user.getLastname(), user.getEmail());
	            } catch (Exception emailException) {
	               
	            }
				return response;
				
			}
			else {
				response.setStatusCode(400);
				response.setMessage("Please give correct OTP");
				return response;
			}
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error occured during OTP verification " + e.getMessage());
		}
		return response;
		
	}


	public Response login(LoginRequest loginRequest) {
		
		Response response = new Response();
		
		if(loginRequest.getEmail()==null || loginRequest.getPassword()==null) {
			response.setStatusCode(400);
			response.setMessage("Fill all the details");
			return response;
		}
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
			
			var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new OurException("User Not Found"));
			
			if(user.isVerified()) {
				var token = jwtUtils.generatedToken(user);
				UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);
			
				response.setUserDTO(userDTO);
				response.setStatusCode(200);
				response.setToken(token);
				response.setRole(user.getRole());
				response.setExpirationTime("7 Days");
				response.setMessage("Successful");
			}
			else {
				
				String otp = generatedRandomAlphanumeric(6);
				user.setOtp(otp);
				
				userRepository.updateVerification(false, otp, user.getId());
				
				emailService.sendOTP(user.getFirstname(), user.getLastname(), user.getEmail(), otp);
				
				response.setUserDTO(Utils.mapUserEntityToUserDTO(user));
				response.setStatusCode(403);
				response.setMessage("User not verified");
				return response;
			}
		}
		catch(AuthenticationException e) {
			response.setStatusCode(400);
			response.setMessage("Invalid Email/Password");
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}


	public Response getSomeFeedback() {
		
		Response response = new Response();
		
		try {
			
			Pageable pageable = PageRequest.of(0, 4);
			List<WebsiteFeedback> feedbacks = feedbackRepository.findSomeFeedback(pageable);
			List<WebsiteFeedbackDTO> feedbackDTOs = new ArrayList<>();
			for(WebsiteFeedback f:feedbacks) {
				WebsiteFeedbackDTO feed = new WebsiteFeedbackDTO();
				User user = f.getUser();
				feed.setFeedback(f.getFeedback());
				feed.setStar(f.getStar());
				feed.setFirstname(user.getFirstname());
				feed.setLastname(user.getLastname());
				feed.setProfilepicture(user.getProfilepicture());
				feed.setId(f.getId());
				feedbackDTOs.add(feed);
			}
			response.setWebsiteFeedbackDTOs(feedbackDTOs);
			response.setStatusCode(200);
			response.setMessage("Success");
			return response;
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}


	public Response userContact(Contact contact) {
		
		Response response = new Response();
		
		if (contact.getFirstname() == null || contact.getFirstname().trim().isEmpty() ||
		        contact.getLastname() == null || contact.getLastname().trim().isEmpty() ||
		        contact.getEmail() == null || contact.getEmail().trim().isEmpty() ||
		        contact.getMessage() == null || contact.getMessage().trim().isEmpty() ||
		        contact.getPhone() == null) {
			response.setMessage("Fill all the fields");
			response.setStatusCode(400);
			return response;
			
		}
		try {
			
			emailService.sendContactMessage(contact.getFirstname(), contact.getLastname(), contact.getEmail(), contact.getMessage());
			emailService.sendAcknowledgmentEmail(contact.getFirstname(), contact.getLastname(), contact.getEmail());
			contactRepository.save(contact);
			response.setMessage("Success");
			response.setStatusCode(200);
			return response;
			
		}
		catch(OurException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		}
		catch(Exception e) {
			response.setStatusCode(500);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	public Response editProfile(EditProfileDTO editProfileDTO) {
	    Response response = new Response();

	    if (editProfileDTO.getFirstname() == null || editProfileDTO.getLastname() == null 
	            || editProfileDTO.getDob() == null ||editProfileDTO.getFirstname().trim().isEmpty() ||editProfileDTO.getLastname().trim().isEmpty()) {
	        
	        response.setStatusCode(400);
	        response.setMessage("Fill all required fields");
	        return response;
	    }

	    try {
	        if (userRepository.existsById(editProfileDTO.getId())) {
	            userRepository.updateEdited(
	                editProfileDTO.getFirstname(),
	                editProfileDTO.getMiddlename(),
	                editProfileDTO.getLastname(),
	                editProfileDTO.getDob(),
	                editProfileDTO.getId()
	            );
	            
	            User userss = userRepository.findById(editProfileDTO.getId()).orElseThrow(()->new OurException("Invalid user"));

	            response.setStatusCode(200);
	            response.setMessage("Success");
	            response.setUserDTO(Utils.mapUserEntityToUserDTO(userss));
	            
	        } else {
	            response.setStatusCode(404);
	            response.setMessage("No user found");
	        }

	        return response;

	    } catch (OurException e) {
	        response.setStatusCode(400);
	        response.setMessage(e.getMessage());
	    } catch (Exception e) {
	        response.setStatusCode(500);
	        response.setMessage("An unexpected error occurred");
	    }

	    return response;
	}


	public Response getUserDetails(Long userId) {
		
		Response response = new Response();
		
		if(userId==null) {
			
			response.setStatusCode(404);
			response.setMessage("user not found");
			return response;
			
		}
		
		try {
			
			User user = userRepository.findById(userId).orElseThrow(()->new OurException("User not found"));
			response.setUserDTO(Utils.mapUserEntityToUserDTO(user));
			response.setStatusCode(200);
			response.setMessage("Success");
			
			return response;
			
		}
		catch (OurException e) {
	        response.setStatusCode(400);
	        response.setMessage(e.getMessage());
	    } catch (Exception e) {
	        response.setStatusCode(500);
	        response.setMessage("An unexpected error occurred");
	    }

	    return response;
		
	}


}
