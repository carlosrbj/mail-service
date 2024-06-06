package com.viasoft.mail_service.service;

import com.viasoft.mail_service.Utils.Utils;
import com.viasoft.mail_service.adapter.EmailAdapter;
import com.viasoft.mail_service.model.EmailAwsDTO;
import com.viasoft.mail_service.model.EmailRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AwsEmailService implements EmailAdapter {

    private final Validator validator;

    @Autowired
    public AwsEmailService(Validator validator) {
        this.validator = validator;
    }

    @Override
    public ResponseEntity<String> send(EmailRequestDTO emailRequest) {
        logger.info("Body with request received: " + emailRequest.toString());
        StringBuilder message;
        try {
            EmailAwsDTO emailAwsDTO = new EmailAwsDTO(emailRequest);
            validateDto(emailAwsDTO);

            // TODO: implementar lógica AWS

            message = new StringBuilder("Sending the mail through AWS");
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

    private void validateDto(EmailAwsDTO emailAwsDTO) {
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(emailAwsDTO);

        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<EmailAwsDTO> violation : violations) {
                builder.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(violations);
        }
    }
}
