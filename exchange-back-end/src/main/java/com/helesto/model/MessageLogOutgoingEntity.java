package com.helesto.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "messages_log_outgoing")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "MessageLogOutgoing.findAllBySenderCompID",
        query = 
                "SELECT * " +                                
                "FROM  messages_log_outgoing " +
                "WHERE sendercompid = :sendercompid "  +
                "ORDER BY id DESC",
        resultClass = MessageLogOutgoingEntity.class
    )
})
public class MessageLogOutgoingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "beginstring", length = 8)
    private String beginstring;

    @Column(name = "sendercompid", length = 64)
    private String sendercompid;

    @Column(name = "sendersubid", length = 64)
    private String sendersubid;

    @Column(name = "senderlocid", length = 64)
    private String senderlocid;

    @Column(name = "targetcompid", length = 64)
    private String targetcompid;

    @Column(name = "targetsubid", length = 64)
    private String targetsubid;

    @Column(name = "targetlocid", length = 64)
    private String targetlocid;

    @Column(name = "session_qualifier", length = 64)
    private String session_qualifier;

    @Column(name = "text")
    private String text;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeginstring() {
        return beginstring;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }    

}
