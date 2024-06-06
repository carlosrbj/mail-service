package com.viasoft.mail_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.mail_service.adapter.EmailServiceFactory;
import com.viasoft.mail_service.model.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailServiceFactory emailServiceFactory;

    @Autowired
    public EmailService(EmailServiceFactory emailServiceFactory) {
        this.emailServiceFactory = emailServiceFactory;
    }

    public ResponseEntity<String> sendEmail(EmailRequestDTO emailRequest) throws JsonProcessingException {
        return emailServiceFactory.getEmailService().send(emailRequest);
    }
}
