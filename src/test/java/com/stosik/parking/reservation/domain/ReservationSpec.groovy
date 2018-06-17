package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.domain.model.Car
import com.stosik.parking.reservation.domain.model.Reservation
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
        given: "we have two reservations (regular, vip) in a system"
        parkingMeter.startReservation(_) >>> [firstEndedReservation, secondEndedReservation]

        when: "we start two reservations and ask for all reservations"
        reservationFacade.startParkmeter(createReservationCommand)
        reservationFacade.startParkmeter(createReservationCommand)

        Page<Reservation> foundReservations = reservationFacade.showAll(new PageRequest(0, 10))

        then: "we have two reservations"
        foundReservations.content.size() == 2
    }

    def "should successfully start parkmeter"()
    {
        given: "we have empty system"
        ReservationRepository repository = Mock()
        reservationFacade = new ReservationConfiguration().reservationFacade(repository, new InMemoryCarRepository(), parkingMeter)
        parkingMeter.startReservation(_) >> fourthReservation

        when: "driver starts reservation"
        reservationFacade.startParkmeter(createReservationCommand)

        then: "park meter has been started for driver's car"
        1 * parkingMeter.startReservation(_)
    }

    def "should successfully stop parkmeter"()
    {
        given: "we have empty system"
        ReservationRepository repository = Mock()
        reservationFacade = new ReservationConfiguration().reservationFacade(repository, new InMemoryCarRepository(), parkingMeter)
        repository.findById(_) >> fourthReservation
        parkingMeter.stopReservation(_) >> firstEndedReservation

        when: "driver stops reservation"
        reservationFacade.stopParkmeter(4L)

        then: "park meter has been stopped for a driver's car"
        1 * parkingMeter.stopReservation(_)
    }

    def "should return 0 as there are no ended reservations for specific day"()
    {
        given: "we have empty system"
        parkingMeter.startReservation(_) >>> [fourthReservation]

        when: "we ask for all reservations on 01.01.2011"
        reservationFacade.startParkmeter(createReservationCommand)

        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 0.0d
    }

    def "should return correct earnings for owner for specific day"()
    {
        given: "we have empty system"
        parkingMeter.startReservation(_) >>> [firstEndedReservation]

        when: "we ask for all reservations on 01.01.2011"
        reservationFacade.startParkmeter(createReservationCommand)

        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 3.0d
    }

    def "should return correct earnings for owner for specific day for multiple evaluators"()
    {
        given: "we have three reservations (regular, vip, regular) in a system"
        parkingMeter.startReservation(_) >>> [firstEndedReservation, secondEndedReservation, fifthReservation]

        when: "we ask for all reservations on 01.01.2011"
        reservationFacade.startParkmeter(createReservationCommand)
        reservationFacade.startParkmeter(createReservationCommand)

        def earningsForSpecificDay = reservationFacade.dailyTakings(new PageRequest(0, 10), specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 10.4d
    }

    def "should check whether driver's car has started park meter"()
    {
        given: "driver started park meter for his car"
        InMemoryCarRepository carRepository = Mock()
        reservationFacade = new ReservationConfiguration().reservationFacade(Mock(InMemoryReservationRepository), carRepository, parkingMeter)
        def car = Mock(Car)

        carRepository.findById(_) >> car
        car.getReservation() >> fifthReservation

        when: "operator checks car"
        def startedParkmeter = reservationFacade.checkVehicle(mondeo.id)

        then: "system return response that there is parked car"
        startedParkmeter
    }

    def "should check driver's car has started park meter"()
    {
        given: "driver started park meter for his car"
        InMemoryCarRepository carRepository = Mock()
        reservationFacade = new ReservationConfiguration().reservationFacade(Mock(InMemoryReservationRepository), carRepository, parkingMeter)

        def car = Mock(Car)

        carRepository.findById(_) >> car
        car.getReservation() >> notStartedReservation

        when: "operator checks car"
        def startedParkmeter = reservationFacade.checkVehicle(mondeo.id)

        then: "system return response that car has not started park meter"
        !startedParkmeter
    }
}
