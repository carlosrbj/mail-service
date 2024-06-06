package com.viasoft.mail_service.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmailAwsDTO{
    @Email
    @NotBlank
    @Size(max = 45)
    private String recipient;

    @Size(max = 60)
    private String recipientName;

    @Email
    @NotBlank
    @Size(max = 45)
    private String sender;

    @Size(max = 120)
    private String subject;

    @Size(max = 256)
    private String content;

    public EmailAwsDTO(EmailRequestDTO request) {
        this.recipient = request.getRecipient();
        this.recipientName = request.getRecipientName();
        this.sender = request.getSender();
        this.subject = request.getSubject();
        this.content = request.getContent();
    }

    public @Email @NotBlank @Size(max = 45) String getRecipient() {
        return recipient;
    }

    public void setRecipient(@Email @NotBlank @Size(max = 45) String recipient) {
        this.recipient = recipient;
    }

    public @Size(max = 60) String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(@Size(max = 60) String recipientName) {
        this.recipientName = recipientName;
    }

    public @Email @NotBlank @Size(max = 45) String getSender() {
        return sender;
    }

    public void setSender(@Email @NotBlank @Size(max = 45) String sender) {
        this.sender = sender;
    }

    public @Size(max = 120) String getSubject() {
        return subject;
    }

    public void setSubject(@Size(max = 120) String subject) {
        this.subject = subject;
    }

    public @Size(max = 256) String getContent() {
        return content;
    }

    public void setContent(@Size(max = 256) String content) {
        this.content = content;
    }
}
