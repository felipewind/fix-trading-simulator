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
                "FROM  orders " +
                "ORDER BY ClOrdID ASC",
        resultClass = OrderEntity.class
    ),
    @NamedNativeQuery(
        name = "Orders.findByClOrdID",
        query = 
                "SELECT * " +                                
                "FROM  orders " +
                "WHERE ClOrdID = :clOrdID",
        resultClass = OrderEntity.class
    )
})
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final char NEW_ORDER_NOT_CONFIRMED = 'Z';
    public static final char CANCEL_NOT_CONFIRMED = 'X';

    public OrderEntity() {}

    public OrderEntity(int clOrdID, char side, char ordStatus, String symbol, double price, double orderQty,
            double cumQty) {
        this.clOrdID = clOrdID;
        this.side = side;
        this.ordStatus = ordStatus;
        this.symbol = symbol;
        this.price = price;
        this.orderQty = orderQty;
        this.cumQty = cumQty;
    }

    // Tag 11
    @Id
    @SequenceGenerator(
            name = "orders_sequence",
            sequenceName = "orders_sequence",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_sequence")
    @Column(name = "ClOrdID")
    private int clOrdID;

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

    public int getClOrdID() {
        return clOrdID;
    }

    public void setClOrdID(int clOrdID) {
        this.clOrdID = clOrdID;
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

}