package com.helesto.dto;

public class MessageLogDto {

    public MessageLogDto(int id, String time, String text) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    private int id;

    private String time;

    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    
}
