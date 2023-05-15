package com.example.laba5;

public class Documents extends Package{
    private final String size = "D";
    private boolean fragile;
    private String arriveReq = "recipient name";
    private String info;

    public Documents(String size, boolean fragile, String arriveReq) {
        super(size, fragile, arriveReq);
    }
}
