package com.helesto.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class OrderDto {

    @Schema(example = "1010")
    private String clOrdId;

    // Tag 54 (Example BUY = '1' / SELL = '2')
    @Schema(example = "1", description = "BUY = '1' / SELL = '2'")
    private String side;
    



    @Schema(example = "LNUX")
    private String symbol;


    @Schema(example = "1000")
    private double orderQty;

    @Schema(example = "56.43")
    private double price;    
    
    @Schema(example = "1234")
    private String account;

    public String getClOrdId() {
        return clOrdId;
    }

    public void setClOrdId(String clOrdId) {
        this.clOrdId = clOrdId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
