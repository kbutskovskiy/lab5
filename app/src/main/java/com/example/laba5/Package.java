package com.example.laba5;

public abstract class Package {
    private String size;
    private boolean fragile;
    private String arriveReq;

    public Package(String size, boolean fragile, String arriveReq) {
        this.size = size;
        this.arriveReq = arriveReq;
        this.fragile = fragile;
    }

    public String getArriveReq() {
        return arriveReq;
    }

    public void setArriveReq(String arriveReq) {
        this.arriveReq = arriveReq;
    }

    public boolean isFragile() {
        return fragile;
    }

    public void setFragile(boolean fragile) {
        this.fragile = fragile;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
