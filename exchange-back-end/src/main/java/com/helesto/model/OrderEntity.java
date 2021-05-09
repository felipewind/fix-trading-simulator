package com.helesto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@NamedNativeQueries({
    @NamedNativeQuery(
        name = "Orders.findAll",
        query = 
                "SELECT * " +                                
                "FROM  orders ",
        resultClass = OrderEntity.class
    ),
    @NamedNativeQuery(
        name = "Orders.findByOrderID",
        query = 
                "SELECT * " +                                
                "FROM  orders " +
                "WHERE OrderID = :orderID",
        resultClass = OrderEntity.class
    ),
    @NamedNativeQuery(
        name = "Orders.findByOrigClOrdID",
        query = 
                "SELECT * " +                                
                "FROM  orders " +
                "WHERE OrigClOrdID = :origClOrdID",
        resultClass = OrderEntity.class
    )    
})
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public OrderEntity() {
    }
  
    // Tag 37
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @Column(name = "OrderID")
    private int orderID;

    // Tag 11
    @Column(name = "ClOrdID")
    private int clOrdID;

    // Tag 41
    @Column(name = "OrigClOrdID")
    private int origClOrdID;

    // Tag 54
    @Column(name = "Side", length = 1)
    private char side;

    // Tag 39
    @Column(name = "OrdStatus", length = 1)
    private char ordStatus;

    // Tag 55
    @Column(name = "Symbol", length = 20)
    private String symbol;

    // Tag 44
    @Column(name = "Price")
    private double price;

    // Tag 38 - Quantity ordered
    @Column(name = "OrderQty")
    private double orderQty;

    // Tag 14 - Total number of shares or contracts filled
    @Column(name = "CumQty")
    private double cumQty;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(int clOrdID) {
        this.clOrdID = clOrdID;
    }

    public int getOrigClOrdID() {
        return origClOrdID;
    }

    public void setOrigClOrdID(int origClOrdID) {
        this.origClOrdID = origClOrdID;
    }

    public char getSide() {
        return side;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public char getOrdStatus() {
        return ordStatus;
    }

    public void setOrdStatus(char ordStatus) {
        this.ordStatus = ordStatus;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(double orderQty) {
        this.orderQty = orderQty;
    }

    public double getCumQty() {
        return cumQty;
    }

    public void setCumQty(double cumQty) {
        this.cumQty = cumQty;
    }

    @Override
    public String toString() {
        return "OrderEntity [clOrdID=" + clOrdID + ", cumQty=" + cumQty + ", ordStatus=" + ordStatus + ", orderID="
                + orderID + ", orderQty=" + orderQty + ", origClOrdID=" + origClOrdID + ", price=" + price + ", side="
                + side + ", symbol=" + symbol + "]";
    }        

}