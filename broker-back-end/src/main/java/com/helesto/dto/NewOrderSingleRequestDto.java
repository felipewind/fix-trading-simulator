package com.helesto.dto;

public class NewOrderSingleRequestDto implements java.io.Serializable {

        private static final long serialVersionUID = 1L;

        // Tag 11
        private String clOrdID;

        // Tag 55
        private String symbol;

        // Tag 54 (Example BUY = '1' / SELL = '2')
        private char side;

        // Tag 38
        private double orderQty;

        // Tag 44
        private double price;

        // Tag 1
        private String account;

        public String getClOrdID() {
                return clOrdID;
        }

        public void setClOrdID(String clOrdID) {
                this.clOrdID = clOrdID;
        }

        public String getSymbol() {
                return symbol;
        }

        public void setSymbol(String symbol) {
                this.symbol = symbol;
        }

        public char getSide() {
                return side;
        }

        public void setSide(char side) {
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