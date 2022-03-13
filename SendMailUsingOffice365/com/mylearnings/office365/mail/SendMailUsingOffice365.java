package com.mylearnings.office365.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMailUsingOffice365
{

	private Properties getMailProperties()
	{
		String host = "smtp.office365.com";
		String portNumber = 587 + "";
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", portNumber);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
		properties.put("mail.imaps.sasl.mechanisms", "XOAUTH2");
		properties.put("mail.imaps.auth.mechanisms", "XOAUTH2");
		properties.put("mail.imaps.auth.plain.disable", "true");
		return properties;
	}

	public boolean sendEMail(String messageBody, String receivers, String subject, String fromMailId, String password) throws Exception
	{
		Properties properties = getMailProperties();
		Session session = Session.getInstance(properties, new Authenticator()
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(fromMailId, password);
			}
		});
		try
		{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromMailId));
			if (receivers.contains(","))
			{
				String splits[] = receivers.split(",");
				for (String toEmailID : splits)
				{
					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.BCC, new InternetAddress(toEmailID));
				}
			}
			else
			{
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers));
			}

			//
			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(messageBody, "text/html");
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);

			// Set Subject: header field
			message.setSubject(subject);
			// Send message
			Transport.send(message);
		}
		catch (Exception e)
		{
			System.err.println("Failed to deliver email" + e.getMessage());
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) throws Exception
	{
		String message = args[0];
		String receivers = args[1];
		String subject = args[2];
		String fromEmail = args[3];
		String password = args[4];
		SendMailUsingOffice365 sendMail = new SendMailUsingOffice365();
		System.out.println("message: " + message);
		System.out.println("receivers: " + receivers);
		System.out.println("subject: " + subject);
		System.out.println("fromEmail: " + fromEmail);
		System.out.println("password: " + "********");
		sendMail.sendEMail(message, receivers, subject, fromEmail, password);
	}
}
