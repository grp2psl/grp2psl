package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/*
 * SERVICE TO SEND MAILS
 */
@Service
public class EmailSenderService {
	@Autowired
	private JavaMailSender sender;
	public void sendEmail(String fromEmail, String toEmail, String body, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		sender.send(message);
		System.out.println("Mail sent..");
	}

}
