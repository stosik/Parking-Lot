package com.stosik.parking.reservation.domain

import com.stosik.parking.base.IntegrationSpec

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ReservationControllerAcceptanceSpec extends IntegrationSpec
{
    def "should show valid path for driver"()
    {
        given:"given system is completely empty"

        when:"driver starts park meter"

        then:"there is one reservation in system"

        when:"driver stops park meter and ask for price of reservation"

        then:"he gets response with cost of reservation"

    }

    def "should show valid path for operator"()
    {
        given:"there are two cars parked, one started park meter and the other didn't"

        when:"operator checks car which didnt start park meter"

        then:"he gets successful response with status"
    }

    def "should show valid path for owner"()
    {
        given:"there are 3 reservations"

        when:"owner checks daily takings"

        then:"he gets response with earned money on that day"

    }
}
