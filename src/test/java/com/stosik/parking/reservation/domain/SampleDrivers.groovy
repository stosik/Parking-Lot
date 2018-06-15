package com.stosik.parking.reservation.domain

trait SampleDrivers
{
    Driver szymon = createDriver(1L, DriverType.REGULAR)
    Driver lukasz = createDriver(2L, DriverType.VIP)
    Driver marcin = createDriver(3L, DriverType.REGULAR)
    Driver henio = createDriver(4L, DriverType.VIP)

    static private Driver createDriver(Long id, DriverType type)
    {
        return Driver
            .builder()
            .id(id)
            .type(type)
            .build()
    }
}