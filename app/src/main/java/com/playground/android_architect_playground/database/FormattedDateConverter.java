package com.playground.android_architect_playground.database;

import android.arch.persistence.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by petnagy on 2017. 07. 01..
 */

public class FormattedDateConverter {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm.sss");

    @TypeConverter
    public static String toFormattedString(Date date) {
        return formatter.format(date);
    }

    @TypeConverter
    public static Date toDate(String formattedDate) {
        Date date = null;
        try {
            date =  formatter.parse(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
