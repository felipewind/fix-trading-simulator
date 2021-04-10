package com.helesto.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class SessionDto {

    @Schema(description = "Describes whether the initiator was already started or not")
    private boolean initiatorStarted;

    @Schema(description = "Is the session logged on")
    private boolean isLoggedOn;

    private String startTime;

    private String[] settings;

    public boolean isInitiatorStarted() {
        return initiatorStarted;
    }

    public void setInitiatorStarted(boolean initiatorStarted) {
        this.initiatorStarted = initiatorStarted;
    }

    public String[] getSettings() {
        return settings;
    }

    public void setSettings(String[] settings) {
        this.settings = settings;
    }

    public boolean isLoggedOn() {
        return isLoggedOn;
    }

    public void setLoggedOn(boolean isLoggedOn) {
        this.isLoggedOn = isLoggedOn;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

}
