package com.viasoft.mail_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.mail_service.adapter.EmailAdapter;
import com.viasoft.mail_service.adapter.ServiceAdapter;
import com.viasoft.mail_service.model.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailAdapter emailAdapter;

    @Autowired
    public EmailService(ServiceAdapter serviceAdapter) {
        this.emailAdapter = serviceAdapter.getEmailService();
    }

    public ResponseEntity<String> sendEmail(EmailRequestDTO emailRequest) throws JsonProcessingException {
        return emailAdapter.send(emailRequest);
    }
}
