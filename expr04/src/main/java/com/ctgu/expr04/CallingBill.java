package com.ctgu.expr04;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public class CallingBill {
    private final static LocalDateTime DST_START_TIME = LocalDateTime.of(2022,3,13,2,0,0);
    private final static LocalDateTime DST_END_TIME = LocalDateTime.of(2022, 11, 6, 2, 0);

    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public CallingBill(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    private boolean compareTime(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).toMillis() > 0;
    }

    private long calculateTimeSpan(LocalDateTime startTime, LocalDateTime endTime) {
        if (compareTime(startTime, DST_START_TIME) && compareTime(endTime, DST_START_TIME)) { // M1 M1
            if (Duration.between(startTime, endTime).toHours() <= 30) {
                if (Duration.between(startTime, endTime).toMillis() > 0) {
                    return Duration.between(startTime, endTime).toMillis();
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else if (compareTime(startTime, DST_START_TIME) && !compareTime(endTime, DST_START_TIME) && compareTime(endTime, DST_END_TIME)){ // M1 M2
            if (Duration.between(startTime, endTime).toHours() <= 30) {
                return Duration.between(startTime, endTime).plusHours(1).toMillis();
            } else {
                return -1;
            }
        } else if (compareTime(startTime, DST_START_TIME) && compareTime(DST_END_TIME, endTime)) { // M1 M3
            return -1;
        } else if (compareTime(DST_START_TIME, startTime) && compareTime(startTime, DST_END_TIME) && compareTime(endTime, DST_START_TIME)){ // M2 M1
            return -1;
        } else if (compareTime(DST_START_TIME, startTime) && compareTime(startTime, DST_END_TIME) && compareTime(DST_START_TIME, endTime) && compareTime(endTime, DST_END_TIME)) {// M2 M2
            if (Duration.between(startTime, endTime).toHours() <= 30) {
                if (Duration.between(startTime, endTime).toMillis() > 0) {
                    return Duration.between(startTime, endTime).toMillis();
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else if (compareTime(DST_START_TIME, startTime) && compareTime(startTime, DST_END_TIME) && compareTime(DST_END_TIME, endTime)) { // M2 M3
            if (Duration.between(startTime, endTime).toHours() <= 30) {
                return Duration.between(startTime, endTime).plusHours(-1).toMillis();
            } else {
                return -1;
            }
        } else if (compareTime(DST_END_TIME, startTime) && compareTime(endTime, DST_END_TIME)) { // M3 M12
            return -1;
        } else if (compareTime(DST_END_TIME, startTime) && compareTime(DST_END_TIME, endTime)) { // M3 M3
            if (Duration.between(startTime, endTime).toHours() <= 30) {
                if (Duration.between(startTime, endTime).toMillis() > 0) {
                    return Duration.between(startTime, endTime).toMillis();
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -2;
        }
    }

    public BigDecimal calculateFee() {
        long timeLength = calculateTimeSpan(this.startTime, this.endTime);
        if (timeLength < 0) {
            return new BigDecimal(Double.toString(timeLength));
        }
        timeLength /= 1000;
        int second = (int) (timeLength % 60);
        timeLength -= second > 0 ? second : 0;
        timeLength /= 60;
        int minute = (int) (timeLength % 60);
        timeLength -= minute > 0 ? minute : 0;
        timeLength /= 60;
        int hour = (int) timeLength;

        if (hour < 0 || hour >= 30 || minute < 0 || second < 0) {
            return new BigDecimal(-1);
        } else {
            int minutes = hour * 60 + minute + (second > 0 ? 1 : 0);
            if (minutes <= 20) {
                return new BigDecimal(Double.toString(minutes* 0.05)).setScale(2,BigDecimal.ROUND_FLOOR);
            } else {
                return new BigDecimal(Double.toString(1 + (minutes - 20) * 0.1)).setScale(2,BigDecimal.ROUND_FLOOR);
            }
        }
    }


}
