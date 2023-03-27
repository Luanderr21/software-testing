package com.ctgu.expr03;

public class CallBill {
    private CallDuration callDuration;

    public CallBill(CallDuration callDuration) {
        this.callDuration = callDuration;
    }

    public double getCost() {
        int hour = callDuration.getHour();
        int minute = callDuration.getMinute();
        int second = callDuration.getSecond();
        System.out.println("");
        if (hour < 0 || hour >= 30 || minute < 0 || second < 0) {
            return -1;
        } else {
            int minutes = hour*60 + minute + (second > 0 ? 1:0);
            if (minutes <= 20) {
                return minutes * 0.05;
            } else {
                return 1 + (minutes - 20) * 0.1;
            }
        }
    }
}
