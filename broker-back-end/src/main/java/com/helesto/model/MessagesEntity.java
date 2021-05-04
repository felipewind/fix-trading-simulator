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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((beginstring == null) ? 0 : beginstring.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        result = prime * result + msgseqnum;
        result = prime * result + ((sendercompid == null) ? 0 : sendercompid.hashCode());
        result = prime * result + ((senderlocid == null) ? 0 : senderlocid.hashCode());
        result = prime * result + ((sendersubid == null) ? 0 : sendersubid.hashCode());
        result = prime * result + ((session_qualifier == null) ? 0 : session_qualifier.hashCode());
        result = prime * result + ((targetcompid == null) ? 0 : targetcompid.hashCode());
        result = prime * result + ((targetlocid == null) ? 0 : targetlocid.hashCode());
        result = prime * result + ((targetsubid == null) ? 0 : targetsubid.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MessagesEntity other = (MessagesEntity) obj;
        if (beginstring == null) {
            if (other.beginstring != null)
                return false;
        } else if (!beginstring.equals(other.beginstring))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        if (msgseqnum != other.msgseqnum)
            return false;
        if (sendercompid == null) {
            if (other.sendercompid != null)
                return false;
        } else if (!sendercompid.equals(other.sendercompid))
            return false;
        if (senderlocid == null) {
            if (other.senderlocid != null)
                return false;
        } else if (!senderlocid.equals(other.senderlocid))
            return false;
        if (sendersubid == null) {
            if (other.sendersubid != null)
                return false;
        } else if (!sendersubid.equals(other.sendersubid))
            return false;
        if (session_qualifier == null) {
            if (other.session_qualifier != null)
                return false;
        } else if (!session_qualifier.equals(other.session_qualifier))
            return false;
        if (targetcompid == null) {
            if (other.targetcompid != null)
                return false;
        } else if (!targetcompid.equals(other.targetcompid))
            return false;
        if (targetlocid == null) {
            if (other.targetlocid != null)
                return false;
        } else if (!targetlocid.equals(other.targetlocid))
            return false;
        if (targetsubid == null) {
            if (other.targetsubid != null)
                return false;
        } else if (!targetsubid.equals(other.targetsubid))
            return false;
        return true;
    }   

}
