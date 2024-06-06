package com.viasoft.mail_service;

import com.viasoft.mail_service.model.EmailOciDTO;
import com.viasoft.mail_service.model.EmailRequestDTO;
import com.viasoft.mail_service.service.OciEmailProcessor;
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
class OciEmailProcessorTest {

    @Mock
    private Validator validator;

    @InjectMocks
    private OciEmailProcessor ociEmailProcessor;

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

        EmailOciDTO emailOciDTO = new EmailOciDTO(emailRequest);

        when(validator.validate(emailOciDTO)).thenReturn(Set.of());

        try {
            ociEmailProcessor.send(emailRequest);
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
            ociEmailProcessor.send(emailRequest);
        } catch (Exception e) {
            assertEquals("Validation error: recipientEmail must be a well-formed email address", e.getMessage());
        }
    }

}
