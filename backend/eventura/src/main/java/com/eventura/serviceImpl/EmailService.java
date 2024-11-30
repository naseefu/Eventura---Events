package com.eventura.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendEmailOnRegister(String firstname, String lastname, String email) {
        String subject = "Welcome to Eventura! Your Event Planning Journey Starts Here";

        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 100%; max-width: 600px; margin: 0 auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "h1 { color: #333; }" +
                "p { color: #555; line-height: 1.6; }" +
                ".button { display: inline-block; padding: 10px 15px; margin-top: 20px; background-color: #4CAF50; color: #ffffff; text-decoration: none; border-radius: 5px; }" +
                ".footer { margin-top: 20px; font-size: 12px; color: #aaa; text-align: center; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Welcome to Eventura!</h1>" +
                "<p>Dear " + firstname + " " + lastname + ",</p>" +
                "<p>We are thrilled to have you join us at Eventura, your go-to platform for managing events effortlessly.</p>" +
                "<p>Whether you're planning a wedding, corporate event, or a simple gathering, we're here to make the process smooth and enjoyable.</p>" +
                "<p>To get started, click the button below and explore the world of event management like never before!</p>" +
                "<a href='[Your Login URL]' class='button'>Get Started</a>" +
                "<div class='footer'>" +
                "Best Regards,<br>Your Eventura Team<br>" +
                "<a href='mailto:eventuraoo07@gmail.com'>eventuraoo07@gmail.com</a>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(content, true); // Set true for HTML content

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Async
    public void sendContactMessage(String firstname, String lastname, String userEmail, String messageContent) {
        String subject = "New Contact Message from " + firstname + " " + lastname;
        
        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 100%; max-width: 600px; margin: 0 auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "h1 { color: #333; }" +
                "p { color: #555; line-height: 1.6; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>New Contact Message</h1>" +
                "<p><strong>From:</strong> " + firstname + " " + lastname + " (" + userEmail + ")</p>" +
                "<p><strong>Message:</strong></p>" +
                "<p>" + messageContent + "</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo("eventuraoo07@gmail.com");
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    
    @Async
    public void sendAcknowledgmentEmail(String firstname, String lastname, String userEmail) {
        String subject = "Thank you for Contacting Eventura!";
        
        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 100%; max-width: 600px; margin: 0 auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "h1 { color: #333; }" +
                "p { color: #555; line-height: 1.6; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Thank You for Reaching Out!</h1>" +
                "<p>Dear " + firstname + " " + lastname + ",</p>" +
                "<p>Thank you for contacting us! We have received your message and will get back to you as soon as possible.</p>" +
                "<p>If you have any further questions, feel free to reply to this email.</p>" +
                "<div class='footer'>" +
                "Best Regards,<br>Your Eventura Team<br>" +
                "<a href='mailto:eventuraoo07@gmail.com'>eventuraoo07@gmail.com</a>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(content, true); // Set true for HTML content

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    @Async
    public void sendOTP(String firstname, String lastname, String userEmail,String otp) {
        String subject = "Account Verification!";
        
        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 100%; max-width: 600px; margin: 0 auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "h1 { color: #333; }" +
                "p { color: #555; line-height: 1.6; }" +
                "h2 {color:#333; text-align:center}"+
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Thank You for Reaching Out!</h1>" +
                "<p>Hi " + firstname+",</p>" +
                "<p>Copy and paste this code into the security code field on your application:</p>" +
                "<p>If you have any further questions, feel free to reply to this email.</p>" +
                "<br></br>"+
                "<h2>"+otp+"</h2>"+
                "<br></br>"+
                "<p>After you enter the code, resubmit the application."+
                "<div class='footer'>" +
                "<br>Your Eventura Team<br>" +
                "<a href='mailto:eventuraoo07@gmail.com'>eventuraoo07@gmail.com</a>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    
    
    @Async
    public void sendBookingConfirmation(String firstname, String lastname, String userEmail, String bookingId, int seatsReserved, String eventName) {
        String subject = "Event Booking Confirmation!";

        String content = "<html>" +
                "<head>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; }" +
                ".container { width: 100%; max-width: 600px; margin: 0 auto; background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "h1 { color: #333; }" +
                "p { color: #555; line-height: 1.6; }" +
                "h2 { color: #333; text-align: center; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Thank You for Booking with Us!</h1>" +
                "<p>Hi " + firstname + " " + lastname + ",</p>" +
                "<p>We're excited to confirm your booking for the event:</p>" +
                "<p><strong>Event Name:</strong> " + eventName + "</p>" +
                "<p><strong>Booking ID:</strong> " + bookingId + "</p>" +
                "<p><strong>Seats Reserved:</strong> " + seatsReserved + "</p>" +
                "<br>" +
                "<p>If you have any questions or need further assistance, feel free to reply to this email.</p>" +
                "<div class='footer'>" +
                "<br>Your Eventura Team<br>" +
                "<a href='mailto:eventuraoo07@gmail.com'>eventuraoo07@gmail.com</a>" +
                "</div>" +
                "</div>" +
                "</body>" +
                "</html>";

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(userEmail);
            helper.setSubject(subject);
            helper.setText(content, true);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }



}
