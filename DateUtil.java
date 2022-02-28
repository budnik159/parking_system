package com.parking;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    private Calendar calendar;
    private static DateUtil instance;

    private DateUtil() {
    }

    public static DateUtil getInstance(){
       if (instance == null){
           instance = new DateUtil();
       }
       return instance;
    }

    public void setCurrentDate(Calendar currentDate){
        calendar = currentDate;
    }

    public Calendar getCurrentDate(){
        if (calendar != null){
            return calendar;
        }
        return Calendar.getInstance();
    }

}


