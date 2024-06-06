package com.viasoft.mail_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.mail_service.Utils.Utils;
import com.viasoft.mail_service.adapter.EmailAdapter;
import com.viasoft.mail_service.model.EmailAwsDTO;
import com.viasoft.mail_service.model.EmailRequestDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AwsEmailProcessor implements EmailAdapter {

    @Override
    public ResponseEntity<String> send(EmailRequestDTO emailRequest) throws JsonProcessingException {
        logger.info("Request with AWS body received: " + Utils.serializeObject(emailRequest));
        StringBuilder message;
        try {
            EmailAwsDTO emailAwsDTO = Utils.validateAwsDto(emailRequest);

            // TODO: implementar lógica AWS

            message = new StringBuilder("Email successfully sent by AWS: " + Utils.serializeObject(emailAwsDTO));
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
