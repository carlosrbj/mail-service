package com.viasoft.mail_service.adapter;

import com.viasoft.mail_service.service.AwsEmailProcessor;
import com.viasoft.mail_service.service.OciEmailProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceAdapter {
    private static final String AWS = "AWS";
    private static final String OCI = "OCI";

    @Value("${mail.integracao}")
    private String mailIntegration;

    private final AwsEmailProcessor awsEmailProcessor;
    private final OciEmailProcessor ociEmailProcessor;

    @Autowired
    public ServiceAdapter(AwsEmailProcessor awsEmailProcessor, OciEmailProcessor ociEmailProcessor) {
        this.awsEmailProcessor = awsEmailProcessor;
        this.ociEmailProcessor = ociEmailProcessor;
    }

    public EmailAdapter getEmailService() {
        return switch (mailIntegration.toUpperCase()) {
            case AWS -> awsEmailProcessor;
            case OCI -> ociEmailProcessor;
            default -> throw new IllegalArgumentException("Invalid mail integration configuration: " + mailIntegration);
        };
    }
}
