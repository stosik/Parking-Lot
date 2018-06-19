package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.domain.model.DriverType
import com.stosik.parking.reservation.domain.model.Reservation
import com.stosik.parking.reservation.dto.CreateReservationCommand

import java.time.LocalDateTime
import java.time.Month

trait SampleReservations
{
    Reservation endedReservationWithCar = createReservation(
        1L,
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 01, 55, 00),
        3.0,
        "EPA123"
    )

    Reservation secondEndedReservation = createReservation(
        2L,
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 02, 55, 00),
        6.0,
        "ELA123"
    )

    Reservation regularEndedReservation = createReservation(
        2L,
        DriverType.REGULAR,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 02, 55, 00),
        6.0,
        "ELA123"
    )

    Reservation vipEndedReservation = createReservation(
        3L,
        DriverType.VIP,
        LocalDateTime.of(2011, Month.JANUARY, 1, 00, 55, 00),
        LocalDateTime.of(2011, Month.JANUARY, 1, 02, 55, 00),
        4.4,
        "ELA123"
    )

    Reservation parkedReservation = createReservation(
        4L,
        DriverType.REGULAR,
        LocalDateTime.now(),
        null,
        null,
        "ELW293"
    )

    Reservation nowReservationWithCar = createReservation(
        4L,
        DriverType.REGULAR,
        LocalDateTime.now(),
        null,
        null,
        "ELW293"
    )

    Reservation reservationWithoutCar = createReservation(
        6L,
        DriverType.REGULAR,
        null,
        null,
        null,
        null
    )

    CreateReservationCommand createReservationCommand = createCommand(
        DriverType.REGULAR,
        "EPA234"
    )

    static private Reservation createReservation(Long id, DriverType driverType, LocalDateTime start, LocalDateTime stop, BigDecimal cost, String licenseId)
    {
        return Reservation
            .builder()
            .id(id)
            .startTime(start)
            .stopTime(stop)
            .driverType(driverType)
            .carLicenseId(licenseId)
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
}
