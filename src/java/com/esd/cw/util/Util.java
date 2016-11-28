package com.esd.cw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Util {

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public static Date getDateFromString(String dateIn) {
        Date dob;
        try {
            dob = format.parse(dateIn);
        } catch (ParseException ex) {
            return null;
        }
        return dob;
    }
}
