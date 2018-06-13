package com.stosik.parking.domain

import spock.lang.Specification

class ParkingMeterSpec extends Specification
{
    def "should start parking meter successfully "()
    {
        given:

        ParkingMeter parkingMeter = new ParkingMeter()

        when:

        parkingMeter.startReservation(new Reservation())

        then:
    }

    def "should stop parking meter successfully "()
    {
        given:

        ParkingMeter parkingMeter = new ParkingMeter()

        when:

        parkingMeter.stopReservation(new Reservation())

        then:
    }
}
