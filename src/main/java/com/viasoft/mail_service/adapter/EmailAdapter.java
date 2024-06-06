package com.viasoft.mail_service.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.mail_service.model.EmailRequestDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;

public interface EmailAdapter {
    Log logger = LogFactory.getLog(EmailAdapter.class.getName());

    ResponseEntity<String> send(EmailRequestDTO emailRequest) throws JsonProcessingException;
}
