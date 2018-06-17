package com.stosik.parking.reservation.domain

import com.stosik.parking.base.IntegrationSpec
import com.stosik.parking.reservation.domain.model.Car
import com.stosik.parking.reservation.dto.CreateReservationCommand
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ReservationControllerAcceptanceSpec extends IntegrationSpec implements SampleReservations
{
    def "should show valid path for driver"()
    {
        given:"given system is completely empty"

        when:"driver starts park meter"
        ResultActions postReservation = mockMvc.perform(post("/parking/driver/start", new CreateReservationCommand(new Car())))

        then:"there is one reservation in system"
        postReservation
            .andExpect(status().isOk())
            .andExpect(content().json("""
                {
                    "content": [
                        {"title":"$clingon.title","type":"$clingon.type"},
                        {"title":"$trumper.title","type":"$trumper.type"}
                    ]
                }"""))

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
