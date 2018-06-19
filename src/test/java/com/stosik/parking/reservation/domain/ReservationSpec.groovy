package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.dto.ReservationDto
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class ReservationSpec extends Specification implements SampleReservations
{
    @Shared
    Date specificDay = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")
    Meter parkingMeter = Mock()
    ReservationRepository reservationRepository = Mock()

    def reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, new ParkingStore(), parkingMeter)

    def "should successfully start parkmeter"()
    {
        given: "we have empty system"

        when: "driver starts reservation"

        reservationFacade.startParkmeter(createReservationCommand)

        then: "park meter has been started for driver's car"

        1 * parkingMeter.startReservation(_)
    }

    def "should successfully stop parkmeter"()
    {
        given: "we have empty system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, new ParkingStore(), new ParkingMeter())
        reservationRepository.save(_) >> endedReservationWithCar

        when: "driver stops park meter"
        reservationFacade.startParkmeter(createReservationCommand)
        ReservationDto reservationDto = reservationFacade.stopParkmeter("EPA234")

        then: "park meter has been stopped for a driver's car"
        reservationDto.getStopTime() != null
        reservationDto.getCost() == 3.0
    }

    def "should inform how much to pay for reservation "()
    {
        given: "we have empty system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, new ParkingStore(), parkingMeter)
        reservationRepository.findById(_) >> Optional.of(regularEndedReservation)

        when: "regular driver asks how much to pay giving ticket with license id of parked car"

        BigDecimal cost = reservationFacade.dispendReservationTicket(1L)

        then: "he is informed how much his reservation cost"

        cost == 6.0
    }

    def "should return correct earnings for owner for specific day"()
    {
        given: "we have empty system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, new ParkingStore(),new ParkingMeter())
        reservationRepository.findByDate(_, _, _) >> Collections.singletonList(endedReservationWithCar)

        when: "we ask for all reservations on 01.01.2011"

        BigDecimal earningsForSpecificDay = reservationFacade.dailyTakings(specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 3.0
    }

    def "should return correct earnings for owner for specific day for multiple evaluators"()
    {
        given: "we have three reservations (regular, vip, regular) in a system"

        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, new ParkingStore(), new ParkingMeter())
        reservationRepository.findByDate(_, _, _) >> Arrays.asList(vipEndedReservation, secondEndedReservation)

        when: "we ask for all reservations on 01.01.2011"

        BigDecimal earningsForSpecificDay = reservationFacade.dailyTakings(specificDay)

        then: "system returns correctly calculated earnings"

        earningsForSpecificDay == 10.4
    }

    def "should check whether driver's car has started park meter"()
    {
        given: "driver started park meter for his car"
        ParkingStore parkingStore = Mock()
        reservationFacade = new ReservationConfiguration().reservationFacade(reservationRepository, parkingStore, new ParkingMeter())
        parkingStore.findAll(_) >> Collections.singletonList(parkedReservation)

        when: "operator checks car"

        def startedParkmeter = reservationFacade.checkVehicle("ELW293")

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
