package com.helesto.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class SessionDto {

    @Schema(description = "Describes whether the initiator was already started or not")
    private boolean initiatorStarted;

    @Schema(description = "Is the session logged on")
    private boolean loggedOn;

    private String startTime;

    private String sessionID;

    private SessionSettingsProperties[] sessionSettingsProperties;

    private String[] sessionSettingsFile;

    public boolean isInitiatorStarted() {
        return initiatorStarted;
    }

    public void setInitiatorStarted(boolean initiatorStarted) {
        this.initiatorStarted = initiatorStarted;
    }

    public boolean isLoggedOn() {
        return loggedOn;
    }

    public void setLoggedOn(boolean loggedOn) {
        this.loggedOn = loggedOn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }   

    public String[] getSessionSettingsFile() {
        return sessionSettingsFile;
    }

    public void setSessionSettingsFile(String[] sessionSettingsFile) {
        this.sessionSettingsFile = sessionSettingsFile;
    }

    public SessionSettingsProperties[] getSessionSettingsProperties() {
        return sessionSettingsProperties;
    }

    public void setSessionSettingsProperties(SessionSettingsProperties[] sessionSettingsProperties) {
        this.sessionSettingsProperties = sessionSettingsProperties;
    }


}
