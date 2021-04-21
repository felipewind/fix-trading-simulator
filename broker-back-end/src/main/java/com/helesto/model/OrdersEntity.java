package com.helesto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class OrdersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ClOrdID")
    private String ClOrdID;



}