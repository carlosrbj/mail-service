package com.viasoft.mail_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.mail_service.Utils.Utils;
import com.viasoft.mail_service.adapter.EmailAdapter;
import com.viasoft.mail_service.model.EmailOciDTO;
import com.viasoft.mail_service.model.EmailRequestDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OciEmailService implements EmailAdapter {

    @Override
    public ResponseEntity<String> send(EmailRequestDTO emailRequest) throws JsonProcessingException {
        logger.info("Body with request received: " + Utils.serializeObject(emailRequest));
        StringBuilder message;
        try {
            EmailOciDTO emailOciDTO = Utils.validateOciDto(emailRequest);

            // TODO: implementar lógica Oracle Cloud

            message = new StringBuilder("Sending the mail through Oracle Cloud: " + Utils.serializeObject(emailOciDTO));
            logger.info(message);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ConstraintViolationException e) {
            message = new StringBuilder("Validation error: " + e.getMessage());
            logger.error(message);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
        } catch (Exception e) {
            message = new StringBuilder("An unexpected error occurred: " + e.getMessage());
            logger.error(message);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message.toString());
        }
    }

}
