package com.master.app.pims.logger;

import org.springframework.stereotype.Component;

@Component
public class UserSession {

    private String userId;
    private String ipAddress;
    private String userAgent;
    private long loginTimestamp;

    public UserSession() {
        this.loginTimestamp = System.currentTimeMillis();
    }

    // Getters and Setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public long getLoginTimestamp() {
        return loginTimestamp;
    }

    public void setLoginTimestamp(long loginTimestamp) {
        this.loginTimestamp = loginTimestamp;
    }

    // Utility method to get a summary of the session
    public String getSessionSummary() {
        return "User ID: " + userId + ", IP: " + ipAddress + ", UserAgent: " + userAgent;
    }
}
