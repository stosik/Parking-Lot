package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.domain.model.Car
import com.stosik.parking.reservation.domain.model.Driver
import com.stosik.parking.reservation.domain.model.DriverType
import com.stosik.parking.reservation.domain.model.Reservation

import java.text.SimpleDateFormat

trait SampleReservations
{
    Reservation firstEndedReservation = createReservation(
        1L,
        createDriver(1L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 01:55:00"),
        3.0d
    )

    Reservation secondEndedReservation = createReservation(
        2L,
        createDriver(2L, DriverType.VIP),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 02:55:00"),
        7.4d
    )

    Reservation thirdEndedReservation = createReservation(
        3L,
        createDriver(3L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-02 04:55:00"),
        17.25d
    )

    Reservation fourthReservation = createReservation(
        4L,
        createDriver(3L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        null,
        3.0d
    )

    Reservation fifthReservation = createReservation(
        5L,
        createDriver(3L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        null,
        null
    )

    Reservation notStartedReservation = createReservation(
        6L,
        createDriver(3L, DriverType.REGULAR),
        null,
        null,
        null
    )

    Car mondeo = createCar(1L)

    static private Reservation createReservation(Long id, Driver driver, Date start, Date stop, Double cost)
    {
        return Reservation
            .builder()
            .id(id)
            .driver(driver)
            .startTime(start)
            .stopTime(stop)
            .cost(cost)
            .build()
    }

    static private Driver createDriver(Long id, DriverType type)
    {
        return Driver
            .builder()
            .id(id)
            .type(type)
            .build()
    }

    static private Car createCar(Long id)
    {
        return Car
            .builder()
            .id(id)
            .driver(createDriver(3L, DriverType.REGULAR))
            .build()
    }
}
