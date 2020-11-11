package com.helesto.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER")

public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ORD_DT")
    private LocalDate orderDate;

    @Id
    @Column(name = "CL_ORD_ID")
    private String clOrdID;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(String clOrdID) {
        this.clOrdID = clOrdID;
    }

}