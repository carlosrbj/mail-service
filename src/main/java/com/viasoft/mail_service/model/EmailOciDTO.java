package com.viasoft.mail_service.model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailOciDTO{
    @Email
    @NotBlank
    @Size(max = 40)
    private String recipientEmail;

    @Size(max = 50)
    private String recipientName;

    @Email
    @NotBlank
    @Size(max = 40)
    private String senderEmail;

    @Size(max = 100)
    private String subject;

    @Size(max = 250)
    private String body;

    public EmailOciDTO(EmailRequestDTO request) {
        this.recipientEmail = request.getRecipient();
        this.recipientName = request.getRecipientName();
        this.senderEmail = request.getSender();
        this.subject = request.getSubject();
        this.body = request.getContent();
    }

    public @Email @NotBlank @Size(max = 40) String getRecipientEmail() {
        return recipientEmail;
    }

    public void setRecipientEmail(@Email @NotBlank @Size(max = 40) String recipientEmail) {
        this.recipientEmail = recipientEmail;
    }

    public @Size(max = 50) String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(@Size(max = 50) String recipientName) {
        this.recipientName = recipientName;
    }

    public @Email @NotBlank @Size(max = 40) String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(@Email @NotBlank @Size(max = 40) String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public @Size(max = 100) String getSubject() {
        return subject;
    }

    public void setSubject(@Size(max = 100) String subject) {
        this.subject = subject;
    }

    public @Size(max = 250) String getBody() {
        return body;
    }

    public void setBody(@Size(max = 250) String body) {
        this.body = body;
    }
}
