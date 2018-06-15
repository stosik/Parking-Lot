package com.stosik.parking.domain

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class ReservationSpec extends Specification implements SampleReservations
{
    @Shared
    Date specificDay = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")

    Meter parkingMeter = Mock()

    def reservationFacade = new ReservationConfiguration().reservationFacade(new InMemoryReservationRepository(), new InMemoryCarRepository(), parkingMeter)

    def "should list reservation in system"()
    {
        given: "we have two reservations (regular, regular) in a system"
        parkingMeter.startReservation(_) >>> [firstEndedReservation, secondEndedReservation]

        when: "we start two reservations and ask for all reservations"
        reservationFacade.startParkmeter(henio)
        reservationFacade.startParkmeter(szymon)

        Page<Reservation> foundReservations = reservationFacade.showAll(new PageRequest(0, 10))

        then: "we have two reservations"
        foundReservations.content.size() == 2
    }

    def "should return 0 as there are no ended reservations for specific day"()
    {
        given: "we have three reservations (regular, vip, regular) in a system"
        parkingMeter.startReservation(_) >>> [fourthReservation, fifthReservation]

        when: "we ask for all reservations on 01.01.2011"
        reservationFacade.startParkmeter(henio)
        reservationFacade.startParkmeter(szymon)

        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earning"

        earningsForSpecificDay == 0.0d
    }

    def "should return correct earnings for owner for specific day"()
    {
        given: "we have three reservations (regular, regular) in a system"
        parkingMeter.startReservation(_) >>> [firstEndedReservation, fifthReservation]

        when: "we ask for all reservations on 01.01.2011"
        reservationFacade.startParkmeter(henio)
        reservationFacade.startParkmeter(szymon)

        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 3.0d
    }

    def "should return correct earnings for owner for specific day for multiple evaluators"()
    {
        given: "we have three reservations (regular, regular) in a system"
        parkingMeter.startReservation(_) >>> [firstEndedReservation, secondEndedReservation, fifthReservation]

        when: "we ask for all reservations on 01.01.2011"
        reservationFacade.startParkmeter(henio)
        reservationFacade.startParkmeter(szymon)

        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 7.4d
    }

    def "should check whether driver's car has started park meter"()
    {
        given: "driver started park meter for his car"

        when: "operator checks car"

        then: "system return response"
    }
}
