package com.stosik.parking.reservation.domain

import com.stosik.parking.reservation.domain.model.Car
import com.stosik.parking.reservation.domain.model.Driver
import com.stosik.parking.reservation.domain.model.DriverType

trait SampleDrivers
{
    Driver szymon = createDriverWithCar(1L, createCarForDriver(1L), DriverType.REGULAR)
    Driver lukasz = createDriverWithCar(2L, createCarForDriver(2L), DriverType.VIP)
    Driver marcin = createDriverWithCar(3L, createCarForDriver(3L), DriverType.REGULAR)
    Driver henio = createDriverWithCar(4L, createCarForDriver(4L), DriverType.VIP)

    static private Driver createDriverWithCar(Long id, Car car, DriverType type)
    {
        return Driver
            .builder()
            .id(id)
            .car(car)
            .type(type)
            .build()
    }

    static private Car createCarForDriver(Long id)
    {
        return Car
            .builder()
            .id(id)
            .build()
    }
}