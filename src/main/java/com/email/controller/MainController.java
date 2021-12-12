package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.email.helper.ResponseMail;
import com.email.service.MailService;

@RestController
@CrossOrigin
public class MainController {

	@Autowired
	private MailService mailService;
	
	@RequestMapping(value = "send", method = RequestMethod.GET)
	public ResponseEntity<ResponseMail> sendMail(
								@RequestParam("to") String to,
								@RequestParam("subject") String subject,
								@RequestParam("body") String body) {
		int key = this.mailService.sendMail(to, subject, body);
		ResponseMail responseMail = new ResponseMail();
		System.out.println(to+" "+subject+" "+body+" "+key);
		switch (key) {
		case 200:
			responseMail.setMessage("Mail has been sent.");
			responseMail.setCode(200);
			break;
		case 500:
			responseMail.setMessage("Mail has not been sent");
			responseMail.setCode(500);
			break;
		default:
			break;
		}
		return ResponseEntity.ok(responseMail);
	}
	
}
