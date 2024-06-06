package com.viasoft.mail_service.Utils;

import com.viasoft.mail_service.model.EmailAwsDTO;
import com.viasoft.mail_service.model.EmailOciDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public abstract class Utils {
    private final Validator validator;

    @Autowired
    protected Utils(Validator validator) {
        this.validator = validator;
    }

    public void validateDto(EmailAwsDTO emailAwsDTO) {
        Set<ConstraintViolation<EmailAwsDTO>> violations = validator.validate(emailAwsDTO);

        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<EmailAwsDTO> violation : violations) {
                builder.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(violations);
        }
    }

    public void validateDto(EmailOciDTO emailOciDTO) {
        Set<ConstraintViolation<EmailOciDTO>> violations = validator.validate(emailOciDTO);
        if (!violations.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<EmailOciDTO> violation : violations) {
                builder.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(violations);
        }
    }
}
