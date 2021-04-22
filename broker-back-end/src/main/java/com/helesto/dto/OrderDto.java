package com.helesto.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class OrderDto {

    public OrderDto() {
        
    }

    public OrderDto(int clOrdId, char side, char ordStatus, String symbol, double price, double orderQty,
            double cumQty) {
        this.clOrdId = clOrdId;
        this.side = side;
        this.ordStatus = ordStatus;
        this.symbol = symbol;
        this.price = price;
        this.orderQty = orderQty;
        this.cumQty = cumQty;
    }

    @Schema(example = "1010")
    private int clOrdId;

    // Tag 54 (Example BUY = '1' / SELL = '2')
    @Schema(example = "1", description = "BUY = '1' / SELL = '2'")
    private char side;

    private char ordStatus;

    @Schema(example = "LNUX")
    private String symbol;

    @Schema(example = "56.43")
    private double price;

    @Schema(example = "1000")
    private double orderQty;

    @Schema(description = "Total number of shares or contracts filled.")
    private double cumQty;

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

    public double getCumQty() {
        return cumQty;
    }

    public void setCumQty(double cumQty) {
        this.cumQty = cumQty;
    }

    public int getClOrdId() {
        return clOrdId;
    }

    public void setClOrdId(int clOrdId) {
        this.clOrdId = clOrdId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(double orderQty) {
        this.orderQty = orderQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
