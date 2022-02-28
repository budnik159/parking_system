package com.parking;

import java.util.Calendar;

public class TimeSpan1 {

    private final long difference;

    public TimeSpan1(Calendar firstDate, Calendar secondDate){
       difference = secondDate.getTimeInMillis() - firstDate.getTimeInMillis();
    }

    public double getTotalHours(){
        return getTotalMinutes() / 60.0;
    }

    public double getTotalMinutes(){
        return difference / 60000.0;
    }

}
