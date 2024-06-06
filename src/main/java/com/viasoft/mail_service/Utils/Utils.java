package com.viasoft.mail_service.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viasoft.mail_service.model.EmailAwsDTO;
import com.viasoft.mail_service.model.EmailOciDTO;
import com.viasoft.mail_service.model.EmailRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Utils {
    static Log logger = LogFactory.getLog(Utils.class.getName());

    private static Validator validator;
    private static ObjectMapper objectMapper;

    @Autowired
    private Utils(Validator validator, ObjectMapper objectMapper) {
        this.validator = validator;
        this.objectMapper = objectMapper;
    }


    public static EmailAwsDTO validateAwsDto(EmailRequestDTO emailRequest) {
        logger.info("Validating data...");
        EmailAwsDTO emailAwsDTO = new EmailAwsDTO(emailRequest);

        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(emailAwsDTO);

        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<EmailAwsDTO> violation : violations) {
                builder.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(violations);
        }

        logger.info("Valid data");
        return emailAwsDTO;
    }

    public static EmailOciDTO validateOciDto(EmailRequestDTO emailRequest) {
        logger.info("Validating data...");
        EmailOciDTO emailOciDTO = new EmailOciDTO(emailRequest);

        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(emailOciDTO);
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<EmailOciDTO> violation : violations) {
                builder.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(violations);
        }

        logger.info("Valid data");
        return emailOciDTO;
    }

    public static String serializeObject(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing object: " + e.getMessage(), e);
            return "Error serializing object";
        }
    }
}
