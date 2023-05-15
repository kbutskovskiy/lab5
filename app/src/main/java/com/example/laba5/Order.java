package com.example.laba5;

public class Order {
    private Firm firm;
    private Package Package;
    private String sourceAddress;

    public Order(Firm firm, Package Package, String sourceAddress, String destinationAddress, int price) {
        this.firm = firm;
        this.Package = Package;
        this.price = price;
        this.destinationAddress = destinationAddress;
        this.sourceAddress = sourceAddress;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public com.example.laba5.Package getPackage() {
        return Package;
    }

    public void setPackage(com.example.laba5.Package aPackage) {
        Package = aPackage;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    private String destinationAddress;
    private int price;
}
