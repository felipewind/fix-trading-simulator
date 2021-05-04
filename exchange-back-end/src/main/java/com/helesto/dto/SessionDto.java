package com.helesto.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class SessionDto {

    @Schema(description = "Describes whether the acceptor was already started or not")
    private boolean acceptorStarted;

    @Schema(description = "Is the session logged on")
    private boolean loggedOn;

    @Schema(description = "Start time of the Session")
    private String startTime;

    @Schema(description = "SessionID")
    private String sessionID;

    @Schema(implementation = SessionSettingsProperties.class, description = "Properties of the session")
    private SessionSettingsProperties[] sessionSettingsProperties;

    @Schema(description = "The same layout of a SessionSettings file")
    private String[] sessionSettingsFile;

    @Schema(description = "Session Storage information")
    private SessionStorage sessionStorage;

    public boolean isAcceptorStarted() {
        return acceptorStarted;
    }

    public void setAcceptorStarted(boolean acceptorStarted) {
        this.acceptorStarted = acceptorStarted;
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

    public SessionStorage getSessionStorage() {
        return sessionStorage;
    }

    public void setSessionStorage(SessionStorage sessionStorage) {
        this.sessionStorage = sessionStorage;
    }

    public static class SessionSettingsProperties {

        private String key;

        private String value;

        public SessionSettingsProperties(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    public static class SessionStorage {

        private String creationTime;

        private int incomingSeqnum;

        private int outgoingSeqnum;

        public int getIncomingSeqnum() {
            return incomingSeqnum;
        }

        public void setIncomingSeqnum(int incomingSeqnum) {
            this.incomingSeqnum = incomingSeqnum;
        }

        public int getOutgoingSeqnum() {
            return outgoingSeqnum;
        }

        public void setOutgoingSeqnum(int outgoingSeqnum) {
            this.outgoingSeqnum = outgoingSeqnum;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }
    }

}
