/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.cw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author alexgray
 */
public class Util
{
    public static Date getDateFromString(String dateIn)
    {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date dob;
        try
        {
            dob = format.parse(dateIn);
        } catch (ParseException ex)
        {
            return null;
        }
        return dob;
    }
}
