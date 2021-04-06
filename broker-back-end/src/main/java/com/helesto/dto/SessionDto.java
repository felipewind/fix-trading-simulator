package com.helesto.dto;

public class SessionDto {

    private boolean start;

    private String[] settings;

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public String[] getSettings() {
        return settings;
    }

    public void setSettings(String[] settings) {
        this.settings = settings;
    }

}
