package com.microservice.backend.services;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {
    public boolean sendEmail(String sender, String receiver, String subject, String msg) throws MessagingException;
}
