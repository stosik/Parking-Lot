package com.stosik.parking.domain

import groovy.transform.CompileStatic

import java.text.SimpleDateFormat

@CompileStatic
trait SampleReservations
{
    Reservation reservation1 = createReservation(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
                                                 new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 01:55:00"))
    Reservation reservation2 = createReservation(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
                                                 new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 02:55:00"))

    static private Reservation createReservation(Date start, Date stop)
    {
        return Reservation
            .builder()
            .startTime(start)
            .stopTime(stop)
            .build()
    }
}
