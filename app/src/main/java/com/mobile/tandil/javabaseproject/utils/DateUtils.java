package com.mobile.tandil.javabaseproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Calendar convertToCalendar(String dt) {

        if (!dt.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            Date date;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
            try {
                date = simpleDateFormat.parse(dt);
                calendar.setTime(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return calendar;
        }
        return null;
    }

    public static String dateAsString(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        String emptyDate = Constants.EMPTY_STRING;
        try {
            emptyDate = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emptyDate;
    }
}
