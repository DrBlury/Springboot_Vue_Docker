package com.microservice.backend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("smtp.Host")
    String smtpHost;

    @Value("smtp.Auth")
    String smtpAuth;

    @Value("smtp.StartTLS")
    String smtpStartTLS;

    @Value("smtp.Port")
    String smtpPort;

    @Value("smtp.SSLTrust")
    String smtpSSLTrust;

    Properties properties = new Properties();

    @PostConstruct
    public void setProperties() {
        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", smtpSSLTrust);
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.ssl.trust", smtpSSLTrust);
    }

    @Override
    public boolean sendEmail(String sender, String receiver, String subject, String msg) throws MessagingException {
        Session session = Session.getInstance(properties, null);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

        return false;
    }
}
