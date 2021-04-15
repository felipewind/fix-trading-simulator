package com.helesto.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Sessions")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Sessions.findBySenderCompID",
        query = 
                "SELECT * " +                                
                "FROM  sessions " +
                "WHERE sendercompid = :sendercompid ",
        resultClass = SessionsEntity.class
    )
})
public class SessionsEntity implements Serializable {

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

    @Column(name = "creation_time")
    private LocalDateTime creation_time;

    @Column(name = "incoming_seqnum")
    private int incoming_seqnum;

    @Column(name = "outgoing_seqnum")
    private int outgoing_seqnum;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public int getIncoming_seqnum() {
        return incoming_seqnum;
    }

    public void setIncoming_seqnum(int incoming_seqnum) {
        this.incoming_seqnum = incoming_seqnum;
    }

    public int getOutgoing_seqnum() {
        return outgoing_seqnum;
    }

    public void setOutgoing_seqnum(int outgoing_seqnum) {
        this.outgoing_seqnum = outgoing_seqnum;
    }

}
