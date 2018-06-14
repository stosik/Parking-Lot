package com.stosik.parking.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

class ReservationSpec extends Specification
{
    ReservationFacade reservationFacade = new ReservationConfiguration().reservationFacade()

    def "shoud list reservations for specific day"()
    {
        given: "we have three reservations in a system"

        reservationFacade.startParkmeter()
        reservationFacade.add(reservation2)

        when: "we ask for all reservations on 01.01.2011"
        Page<Reservation> foundReservations = reservationFacade.(new PageRequest(0, 10))

        then: "system returns the films we have added"

        foundReservations.contains(trumper)
        foundReservations.contains(clingon)
    }

    def "should show a price for reservation time"()
    {
    }
}
