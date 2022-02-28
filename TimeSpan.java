package com.parking;

import java.util.Calendar;


public class TimeSpan {

    private final long difference;

    public TimeSpan(long difference){
        this.difference = difference;
    }

    public double getTotalHours(){
        return getTotalMinutes() / 60.0;
    }

    public double getTotalMinutes(){
        return difference / 60000.0;
    }

    public static TimeSpan diffDates(Calendar firstDate, Calendar secondDate){
        return new TimeSpan(secondDate.getTimeInMillis() - firstDate.getTimeInMillis());
    }

}
