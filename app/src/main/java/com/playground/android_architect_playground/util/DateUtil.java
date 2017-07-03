package com.playground.android_architect_playground.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by petnagy on 2017. 07. 02..
 */

public class DateUtil {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm.sss");

    public static String toFormattedString(Date date) {
        return formatter.format(date);
    }
}
