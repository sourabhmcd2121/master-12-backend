package com.master.app.pims.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileStorageConfig {

    @Value("${file.storage.path}")
    private String pdfStoragePath;

    public String getPdfStoragePath() {
        return pdfStoragePath;
    }
}