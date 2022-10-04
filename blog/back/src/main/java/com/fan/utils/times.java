package com.fan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class times {

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }
}
