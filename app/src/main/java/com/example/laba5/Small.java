package com.example.laba5;

public class Small extends Package{
    private final String size = "S";
    private boolean fragile;
    private final String arriveReq = "none";

    public Small(String size, boolean fragile, String arriveReq) {
        super(size, fragile, arriveReq);
    }
}
