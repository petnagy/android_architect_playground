package com.playground.android_architect_playground.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by petnagy on 2017. 07. 08..
 */

public class DateTypeConverter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

}
