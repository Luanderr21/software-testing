package com.ctgu.expr03;

public class CallDuration {
    private static int _RATIO_MILLISECOND_SECOND = 1000;
    private static int _RATIO_SECOND_MINUTE = 60;
    private static int _RATIO_MINUTE_HOUR = 60;

    private int hour;
    private int minute;
    private int second;
    private timeCrossType timeType;

    public CallDuration(long start_time_stamp, long end_time_stamp, timeCrossType timeTypeDiff) throws WrongDurationException {
        this.timeType = timeTypeDiff;
        long nowTime = System.currentTimeMillis();
        if (start_time_stamp < 0L || start_time_stamp > nowTime || end_time_stamp < 0L || end_time_stamp > nowTime)
            throw new WrongDurationException();
        long time_duration = end_time_stamp - start_time_stamp;


        switch (this.timeType) {
            case NORMAL:
                break;
            case GETIN:
                time_duration -= _RATIO_MILLISECOND_SECOND * _RATIO_SECOND_MINUTE * _RATIO_MINUTE_HOUR;
                break;
            case GETOUT:
                time_duration += _RATIO_MILLISECOND_SECOND * _RATIO_SECOND_MINUTE * _RATIO_MINUTE_HOUR;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + this.timeType);
        }

        long seconds = time_duration/_RATIO_MILLISECOND_SECOND;
        this.second = (int)seconds % _RATIO_SECOND_MINUTE;
        this.minute = (int)seconds / _RATIO_SECOND_MINUTE;
        this.hour = this.minute / _RATIO_MINUTE_HOUR;
        this.minute %= 60;


    }

    public String getFormat() {
        return this.second+"s, "+this.minute+"min, "+this.hour+"h";
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
}

