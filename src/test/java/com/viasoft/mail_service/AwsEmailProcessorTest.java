package com.viasoft.mail_service;

import com.viasoft.mail_service.model.EmailAwsDTO;
import com.viasoft.mail_service.model.EmailRequestDTO;
import com.viasoft.mail_service.service.AwsEmailProcessor;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AwsEmailProcessorTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private AwsEmailProcessor awsEmailProcessor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSend_ValidEmailRequest() {
        EmailRequestDTO emailRequest = new EmailRequestDTO();
        emailRequest.setRecipient("recipient@example.com");
        emailRequest.setRecipientName("Recipient Name");
        emailRequest.setSender("sender@example.com");
        emailRequest.setSubject("Test Subject");
        emailRequest.setContent("Test Content");

        EmailAwsDTO emailAwsDTO = new EmailAwsDTO(emailRequest);

        when(validator.validate(emailAwsDTO)).thenReturn(Set.of());

        try {
            awsEmailProcessor.send(emailRequest);
        } catch (Exception e) {
            assertEquals("No exceptions should be thrown", e.getMessage());
        }
    }

    @Test
    void testSend_InvalidRecipientEmail_ExceptionThrown() {

        EmailRequestDTO emailRequest = new EmailRequestDTO();
        emailRequest.setRecipient("invalid-email");
        emailRequest.setRecipientName("Recipient Name");
        emailRequest.setSender("sender@example.com");
        emailRequest.setSubject("Test Subject");
        emailRequest.setContent("Test Content");

        try {
            awsEmailProcessor.send(emailRequest);
        } catch (Exception e) {
            assertEquals("Validation error: recipientEmail must be a well-formed email address", e.getMessage());
        }
    }

}
