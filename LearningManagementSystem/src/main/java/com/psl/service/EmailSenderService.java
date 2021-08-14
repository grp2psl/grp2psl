package com.psl.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public static final Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
	private final String logPrefix = "Email Sender Service - ";

	public void sendEmail(String fromEmail, String toEmail, String body, String subject) {
		LOGGER.info(logPrefix+"Sending email from - "+fromEmail+" to - "+toEmail+" with subject - "+subject+" and body - "+body);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(fromEmail);
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		sender.send(message);
		System.out.println("Mail sent..");
		LOGGER.info(logPrefix+"Mail sent");
	}

}
