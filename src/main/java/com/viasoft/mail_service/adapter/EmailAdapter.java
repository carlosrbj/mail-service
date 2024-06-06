package com.viasoft.mail_service.adapter;

import com.viasoft.mail_service.model.EmailRequestDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public interface EmailAdapter extends Serializable {
    Log logger = LogFactory.getLog(EmailAdapter.class.getName());

    ResponseEntity<String> send(EmailRequestDTO emailRequest);
}
