package com.stosik.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@UtilityClass
public class DateUtils
{
    public int hoursDifference(LocalDateTime startTime, LocalDateTime stopTime)
    {
        return (int) ChronoUnit.HOURS.between(startTime, stopTime);
    }
}
