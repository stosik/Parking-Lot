package com.stosik.parking.domain

import java.text.SimpleDateFormat

trait SampleReservations implements SampleDrivers
{
    Reservation firstEndedReservation = createReservation(
        1L,
        createDriver(1L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 01:55:00")
    )

    Reservation secondEndedReservation = createReservation(
        2L,
        createDriver(2L, DriverType.VIP),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 02:55:00")
    )

    Reservation thirdEndedReservation = createReservation(
        3L,
        createDriver(3L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-02 02:55:00")
    )

    Reservation fourthReservation = createReservation(
        4L,
        createDriver(3L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        null
    )

    Reservation fifthReservation = createReservation(
        5L,
        createDriver(3L, DriverType.REGULAR),
        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00"),
        null
    )

    Car mondeo = createCar(1L)

    static private Reservation createReservation(Long id, Driver driver, Date start, Date stop)
    {
        return Reservation
            .builder()
            .id(id)
            .driver(driver)
            .startTime(start)
            .stopTime(stop)
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
