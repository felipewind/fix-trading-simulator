package com.helesto.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class OrdersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "DT_ORD")
    private LocalDate dateOrder;

    @Id
    @Column(name = "NR_SEQ_ORD")
    private int numberSequentialOrder;

    public int getNumberSequentialOrder() {
        return numberSequentialOrder;
    }

    public void setNumberSequentialOrder(int numberSequentialOrder) {
        this.numberSequentialOrder = numberSequentialOrder;
    }    

}