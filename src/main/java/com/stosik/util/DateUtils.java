package com.stosik.util;

import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class DateUtils
{
    public int hoursDifference(Date startTime, Date stopTime)
    {
        final int MILLI_TO_HOUR = 1000 * 60 * 60;
        return (int) (stopTime.getTime() - startTime.getTime()) / MILLI_TO_HOUR;
    }
}
