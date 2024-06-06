package com.viasoft.mail_service.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.viasoft.mail_service.model.EmailRequestDTO;
import com.viasoft.mail_service.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Operation(description = "Sends the email according to the selected provider")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Email sent successfully to the selected provider"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
    })
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequestDTO emailRequest) throws JsonProcessingException {
        return emailService.sendEmail(emailRequest);
    }
}
