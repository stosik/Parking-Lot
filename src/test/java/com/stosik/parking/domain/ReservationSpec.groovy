package com.stosik.parking.domain

import org.springframework.data.domain.PageRequest
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class ReservationSpec extends Specification
{
    @Shared
    Date specificDay = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")

    ReservationFacade reservationFacade = new ReservationConfiguration().reservationFacade()

    def "should return good earnings for reservations for specific day"()
    {
        given: "we have three reservations (regular, vip, regular) in a system"
        reservationFacade.startParkmeter()
        reservationFacade.startParkmeter()

        when: "we ask for all reservations on 01.01.2011"
        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earning"

        earningsForSpecificDay == 1.0d
    }

    def "should successfully check whether driver's car has started park meter"()
    {
        given: "driver started park meter for his car"

        when: "operator checks car"

        then: "system return response"

    }

    def "should unsuccessfully check whether driver's car has started park meter"()
    {
        given: "driver didn't start park meter for his car"

        when: "operator checks car"

        then: "system return response"
    }
}
