package com.Social11.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    public static Long generateOtp(){
        return (long)((Math.random()*9*Math.pow(5,6))+Math.pow(5,6));
    }


    public static String getCurrentDateAndTime(){
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

}
