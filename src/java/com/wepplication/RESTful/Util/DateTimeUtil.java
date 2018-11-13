package com.wepplication.RESTful.Util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.joda.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTimeUtil {
    public static Timestamp now() {
        try {
            return new Timestamp(new Date().getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static List<String> getCalendar(Integer prev, Integer next){
        if(prev == null) prev = 0;
        if(next == null) next = 11;

        List<String> allDates = new ArrayList<String>();
        LocalDate now = LocalDate.now();
        for(int i = prev; i < next +1; i++){
            LocalDate tmp = now.plusMonths(i);
            int month = tmp.getMonthOfYear();
            String strMonth = String.valueOf(month);
            if(month < 10) strMonth = "0".concat(strMonth);
            String strDate = String.valueOf(tmp.getYear()).concat(".").concat(strMonth);
            allDates.add(strDate);
        }

        return allDates;
    }

    public static String getCurrentYM(){
        LocalDate now = LocalDate.now();

        return toCalendarString(now);
    }

    public static String toCalendarString(LocalDate date){
        int month = date.getMonthOfYear();
        String str = String.valueOf(month);

        if(month <10) str = "0".concat(str);

        return String.valueOf(date.getYear()).concat(".").concat(str);
    }

    public static int getDate(){
        return LocalDate.now().dayOfMonth().getMaximumValue();
    }

    public static java.sql.Date getSqlDate(String date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date parsed = format.parse(date);
            return new java.sql.Date(parsed.getTime());
        } catch (Exception e) {
            return null;
        }
    }
}
