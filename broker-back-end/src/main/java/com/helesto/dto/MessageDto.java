package com.helesto.dto;

public class MessageDto {    

    public MessageDto(int msgseqnum, String message) {
        this.msgseqnum = msgseqnum;
        this.message = message;
    }

    private int msgseqnum;

    private String message;

    public int getMsgseqnum() {
        return msgseqnum;
    }

    public void setMsgseqnum(int msgseqnum) {
        this.msgseqnum = msgseqnum;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
