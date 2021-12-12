package com.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public int sendMail(String to, String subject, String body) {
		System.out.println("Mail sent to "+to);
		SimpleMailMessage createMail = this.createMail(to, subject, body);
		try {
			this.javaMailSender.send(createMail);	
		} catch (Exception e) {
			System.out.println(e);
			return 500;
		}
		return 200;
	}

	public SimpleMailMessage createMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(body);
        message.setFrom("Email Service <no-reply@gmail.com>");
		return message;
	}
	
	
}
