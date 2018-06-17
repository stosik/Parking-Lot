package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.domain.model.Car
import com.stosik.parking.reservation.domain.model.DriverType
import com.stosik.parking.reservation.domain.model.Reservation
import com.stosik.parking.reservation.dto.CreateReservationCommand

import java.time.LocalDateTime
import java.time.Month

trait SampleReservations
{
    Reservation firstEndedReservation = createReservation(
        1L,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        3.0d
    )

    Reservation secondEndedReservation = createReservation(
        2L,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 2, 01, 55, 00),
        7.4d
    )

    Reservation thirdEndedReservation = createReservation(
        3L,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 4, 01, 55, 00),
        17.25d
    )

    Reservation fourthReservation = createReservation(
        4L,
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        null,
        3.0d
    )

    Reservation fifthReservation = createReservation(
        5L,
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        null,
        null
    )

    Reservation notStartedReservation = createReservation(
        6L,
        null,
        null,
        null
    )

    CreateReservationCommand createReservationCommand = createCommand(
        DriverType.REGULAR,
        createCar(1L)
    )

    Car mondeo = createCar(1L)

    static private Reservation createReservation(Long id, LocalDateTime start, LocalDateTime stop, Double cost)
    {
        return Reservation
            .builder()
            .id(id)
            .startTime(start)
            .stopTime(stop)
            .cost(cost)
            .build()
    }

    static private CreateReservationCommand createCommand(DriverType driverType, Car car)
    {
        return CreateReservationCommand
            .builder()
            .driverType(driverType)
            .car(car)
            .build()
    }

    static private Car createCar(Long id)
    {
        return Car
            .builder()
            .id(id)
            .build()
    }
}
