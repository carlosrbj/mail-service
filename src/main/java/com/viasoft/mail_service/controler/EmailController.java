package com.viasoft.mail_service.controler;

import com.viasoft.mail_service.model.EmailRequestDTO;
import com.viasoft.mail_service.service.EmailService;
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

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody EmailRequestDTO emailRequest){
        return emailService.sendEmail(emailRequest);
    }
}
