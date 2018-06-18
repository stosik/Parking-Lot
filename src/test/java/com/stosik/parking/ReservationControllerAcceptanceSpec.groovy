package com.stosik.parking

import com.stosik.parking.base.IntegrationSpec
import com.stosik.parking.reservation.domain.SampleReservations
import org.springframework.test.web.servlet.ResultActions

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ReservationControllerAcceptanceSpec extends IntegrationSpec implements SampleReservations
{

    def "should show valid path for driver"()
    {
        given:"given system is completely empty"

        when:"driver starts park meter"

        ResultActions startReservation = mockMvc.perform(post("/parking/driver/start", createReservationCommand))

        then:"there is one reservation in system"

        startReservation
            .andExpect(status().isOk())
            .andExpect(content().json("""
                {
                    
                }"""))

        when:"driver stops park meter and ask for price of reservation"

        ResultActions stopReservation = mockMvc.perform(put("/parking/driver/stop").param("id", "1"))
        ResultActions dispendedCost = mockMvc.perform(get("/parking/driver/cost").param("id", "1"))

        then:"he gets response with cost of reservation"
        dispendedCost
            .andExpect(status().isOk())
            .andExpect(content().json("""
                {
                    
                }"""))
    }

    def "should show valid path for operator"()
    {
        given:"there are two cars parked, one started park meter and the other didn't"

        when:"operator checks car which didn't start park meter"
        ResultActions stopReservation = mockMvc.perform(get("/parking/operator/cars").param("licenseId", "EPA123"))

        then:"he gets successful response with status"
    }

    def "should show valid path for owner"()
    {
        given:"there are 3 ended on that day (vip, regular, regular) reservations"

        when:"owner checks daily takings"
        ResultActions stopReservation = mockMvc.perform(get("/parking/owner/earnings").param("specificDay", "12-12-2012"))

        then:"he gets response with earned money on that day"
    }
}
