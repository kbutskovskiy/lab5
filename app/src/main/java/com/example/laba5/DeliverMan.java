package com.example.laba5;

import java.util.ArrayList;

public class DeliverMan {
    private String FIO;
    private String account;
    private boolean carInStock;
    private boolean docDeliver;

    public DeliverMan(String FIO, boolean docDeliver, boolean carInStock) {
        this.carInStock = carInStock;
        this.docDeliver = docDeliver;
        this.carInStock = carInStock;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public boolean isDocDeliver() {
        return docDeliver;
    }

    public void setDocDeliver(boolean docDeliver) {
        this.docDeliver = docDeliver;
    }

    public boolean isCarInStock() {
        return carInStock;
    }

    public void setCarInStock(boolean carInStock) {
        this.carInStock = carInStock;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    private ArrayList<Order> orderList = new ArrayList<>();
}
