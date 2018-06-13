package com.stosik.parking.domain

import spock.lang.Specification

class ParkingMeterSpec extends Specification
{
    def "should start parking meter successfully"()
    {
        given:

        def parkingMeter = new ParkingMeter()

        when:

        def reservation = parkingMeter.startReservation(new Reservation())

        then:

        reservation.id == 1L
    }

    def "should stop parking meter successfully"()
    {
        given:

        def parkingMeter = new ParkingMeter()

        when:

        def reservation = parkingMeter.stopReservation(new Reservation())

        then:

        reservation.id == 1L
    }
}
