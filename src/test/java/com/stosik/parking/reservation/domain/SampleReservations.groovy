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
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        3.0
    )

    Reservation secondEndedReservation = createReservation(
        2L,
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 02, 55, 00),
        7.4
    )

    Reservation nowReservation = createReservationWithCar(
        4L,
        DriverType.REGULAR,
        LocalDateTime.now(),
        null,
        null,
        createCar(1L, "EPA213", null)
    )

    Reservation notStartedReservationWithCar = createReservationWithCar(
        6L,
        DriverType.REGULAR,
        null,
        null,
        null,
        createCar(1L, "EPA213", null)
    )

    Reservation reservationWithCar = createReservationWithCar(
        4L,
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        null,
        0.0,
        createCar(1L, "EPA213", null)
    )

    Reservation reservationWithCarInt = createReservationWithCar(
        null,
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        3.0,
        createCar(null, "EPA213", null)
    )


    CreateReservationCommand createReservationCommand = createCommand(
        DriverType.REGULAR,
        "EPA234"
    )

    Car carWithReservation = createCar(
        1L,
        "ELP234",
        createReservation(
            6L,
            DriverType.REGULAR,
            LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
            null,
            null
        )
    )

    Car carWithoutReservation = createCar(
        1L,
        "ELP234",
        createReservation(
            6L,
            DriverType.REGULAR,
            null,
            null,
            null
        )
    )

    static private Reservation createReservation(Long id, DriverType driverType, LocalDateTime start, LocalDateTime stop, BigDecimal cost)
    {
        return Reservation
            .builder()
            .id(id)
            .startTime(start)
            .stopTime(stop)
            .driverType(driverType)
            .cost(cost)
            .build()
    }

    static private Reservation createReservationWithCar(Long id, DriverType driverType, LocalDateTime start, LocalDateTime stop, BigDecimal cost, Car car)
    {
        return Reservation
            .builder()
            .id(id)
            .startTime(start)
            .stopTime(stop)
            .car(car)
            .driverType(driverType)
            .cost(cost)
            .build()
    }

    static private CreateReservationCommand createCommand(DriverType driverType, String carLicenseId)
    {
        return CreateReservationCommand
            .builder()
            .driverType(driverType)
            .carLicenseId(carLicenseId)
            .build()
    }

    static private Car createCar(Long id, String licenseId, Reservation reservation)
    {
        return Car
            .builder()
            .id(id)
            .licenseId(licenseId)
            .reservation(reservation)
            .build()
    }
}
