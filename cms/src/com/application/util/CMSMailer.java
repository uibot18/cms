package com.application.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CMSMailer {

	public static void send(EmailPayload payload) {
		
		final String username = "uibot18@gmail.com";
		final String password = "ui@12345";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress( username ));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse( payload.getTo() ));
			message.setSubject( payload.getSubject() );
			message.setText( payload.getMessage() );

			Thread t= new Thread( new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Transport.send(message);
						System.out.println("Mail sent");
					} catch (MessagingException e) { e.printStackTrace(); }
				}
			});
			t.start();

		} catch (MessagingException e) { e.printStackTrace(); }
		
	}
}
