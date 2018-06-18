package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.dto.ReservationDto
import org.springframework.data.domain.PageRequest
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class ReservationSpec extends Specification implements SampleReservations
{
    @Shared
    Date specificDay = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")
    Meter parkingMeter = Mock()
    CarRepository carRepository = Mock()
    ReservationRepository reservationRepository = Mock()

    def reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, carRepository, parkingMeter)

    def "should successfully start parkmeter"()
    {
        given: "we have empty system"

        parkingMeter.startReservation(_) >> reservationWithCar
        reservationRepository.save(_) >> reservationWithCar

        when: "driver starts reservation"

        reservationFacade.startParkmeter(createReservationCommand)

        then: "park meter has been started for driver's car"

        1 * parkingMeter.startReservation(_)
    }

    def "should successfully stop parkmeter"()
    {
        given: "we have empty system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, carRepository, new ParkingMeter())
        reservationRepository.findById(_) >> Optional.of(nowReservation)

        when: "driver stops park meter"

        ReservationDto reservationDto = reservationFacade.stopParkmeter(4L)

        then: "park meter has been stopped for a driver's car"
        reservationDto.getStopTime() != null
        reservationDto.getCost() == 1.0
    }

    def "should inform how much to pay for reservation "()
    {
        given: "we have empty system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, carRepository, parkingMeter)
        reservationRepository.findById(_) >> Optional.of(firstEndedReservation)

        when: "driver asks how much to pay giving ticket with id reservation"

        BigDecimal cost = reservationFacade.dispendReservationTicket(1L)

        then: "he is informed how much his reservation cost"

        cost == 3.0
    }

    def "should return correct earnings for owner for specific day"()
    {
        given: "we have empty system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, carRepository, new ParkingMeter())
        reservationRepository.findByDate(_, _, _) >> Collections.singletonList(firstEndedReservation)

        when: "we ask for all reservations on 01.01.2011"

        BigDecimal earningsForSpecificDay = reservationFacade.dailyTakings(specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 3.0
    }

    def "should return correct earnings for owner for specific day for multiple evaluators"()
    {
        given: "we have three reservations (regular, vip, regular) in a system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, carRepository, new ParkingMeter())
        reservationRepository.findByDate(_, _, _) >> Arrays.asList(firstEndedReservation, secondEndedReservation)

        when: "we ask for all reservations on 01.01.2011"

        BigDecimal earningsForSpecificDay = reservationFacade.dailyTakings(specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 10.4
    }

    def "should check whether driver's car has started park meter"()
    {
        given: "driver started park meter for his car"

        carRepository.findByLicenseId(_) >> Optional.of(carWithReservation)

        when: "operator checks car"

        def startedParkmeter = reservationFacade.checkVehicle("EPAYTSG")

        then: "system return response that there is parked car"

        startedParkmeter
    }

    def "should check driver's car has started park meter"()
    {
        given: "driver started park meter for his car"

        carRepository.findByLicenseId(_) >> Optional.of(carWithoutReservation)

        when: "operator checks car"

        def startedParkmeter = reservationFacade.checkVehicle("EPAYTSG")

        then: "system return response that car has not started park meter"

        !startedParkmeter
    }
}
