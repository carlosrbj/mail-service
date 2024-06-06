package com.viasoft.mail_service.adapter;

import com.viasoft.mail_service.service.AwsEmailService;
import com.viasoft.mail_service.service.OciEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceFactory {

    @Value("${mail.integracao}")
    private String mailIntegration;

    private final AwsEmailService awsEmailService;
    private final OciEmailService ociEmailService;

    @Autowired
    public EmailServiceFactory(AwsEmailService awsEmailService, OciEmailService ociEmailService) {
        this.awsEmailService = awsEmailService;
        this.ociEmailService = ociEmailService;
    }

    public EmailAdapter getEmailService() {
        return switch (mailIntegration.toUpperCase()) {
            case "AWS" -> awsEmailService;
            case "OCI" -> ociEmailService;
            default -> throw new IllegalArgumentException("Invalid mail integration configuration: " + mailIntegration);
        };
    }
}
