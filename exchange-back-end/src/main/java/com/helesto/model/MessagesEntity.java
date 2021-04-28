package com.helesto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "messages")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Messages.findAllBySenderCompID",
        query = 
                "SELECT * " +                                
                "FROM  messages " +
                "WHERE sendercompid = :sendercompid "  +
                "ORDER BY msgseqnum DESC",
        resultClass = MessagesEntity.class
    )
})
public class MessagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "beginstring", length = 8)
    private String beginstring;

    @Id
    @Column(name = "sendercompid", length = 64)
    private String sendercompid;

    @Id
    @Column(name = "sendersubid", length = 64)
    private String sendersubid;

    @Id
    @Column(name = "senderlocid", length = 64)
    private String senderlocid;

    @Id
    @Column(name = "targetcompid", length = 64)
    private String targetcompid;

    @Id
    @Column(name = "targetsubid", length = 64)
    private String targetsubid;

    @Id
    @Column(name = "targetlocid", length = 64)
    private String targetlocid;

    @Id
    @Column(name = "session_qualifier", length = 64)
    private String session_qualifier;

    @Id
    @Column(name = "msgseqnum")
    private int msgseqnum;

    @Column(name = "message")
    private String message;

    public String getBeginstring() {
        return beginstring;
    }

    public void setBeginstring(String beginstring) {
        this.beginstring = beginstring;
    }

    public String getSendercompid() {
        return sendercompid;
    }

    public void setSendercompid(String sendercompid) {
        this.sendercompid = sendercompid;
    }

    public String getSendersubid() {
        return sendersubid;
    }

    public void setSendersubid(String sendersubid) {
        this.sendersubid = sendersubid;
    }

    public String getSenderlocid() {
        return senderlocid;
    }

    public void setSenderlocid(String senderlocid) {
        this.senderlocid = senderlocid;
    }

    public String getTargetcompid() {
        return targetcompid;
    }

    public void setTargetcompid(String targetcompid) {
        this.targetcompid = targetcompid;
    }

    public String getTargetsubid() {
        return targetsubid;
    }

    public void setTargetsubid(String targetsubid) {
        this.targetsubid = targetsubid;
    }

    public String getTargetlocid() {
        return targetlocid;
    }

    public void setTargetlocid(String targetlocid) {
        this.targetlocid = targetlocid;
    }

    public String getSession_qualifier() {
        return session_qualifier;
    }

    public void setSession_qualifier(String session_qualifier) {
        this.session_qualifier = session_qualifier;
    }

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
