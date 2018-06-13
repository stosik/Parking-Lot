package com.stosik.parking.domain

import groovy.transform.CompileStatic

@CompileStatic
trait SampleReservations
{
    Reservation reservation1 = createReservation()
    Reservation reservation2 = createReservation()

    static private Reservation createReservation()
    {
        return Reservation
            .builder()
            .build()
    }
}
