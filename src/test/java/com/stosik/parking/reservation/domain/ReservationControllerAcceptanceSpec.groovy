package com.stosik.parking.reservation.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.stosik.parking.base.IntegrationSpec
import com.stosik.parking.reservation.domain.model.DriverType
import com.stosik.parking.reservation.dto.ReservationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Shared

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class ReservationControllerAcceptanceSpec extends IntegrationSpec implements SampleReservations
{
    @Shared
    ObjectMapper mapper = new ObjectMapper()

    @Autowired
    ReservationRepository reservationRepository

    def "should show valid path for driver"()
    {
        given: "given system is completely empty"

        def content = mapper.writeValueAsString(createReservationCommand)

        when: "driver starts park meter"

        ResultActions startReservationResponse = mockMvc
            .perform(
                post("/parking/driver/start")
                    .contentType(contentType)
                    .content(content)
             )
            .andExpect(status().isOk())

        then: "there is one reservation in system"

        ReservationDto reservationDto = mapper.readValue(startReservationResponse.andReturn().response.contentAsString, ReservationDto.class)
        reservationDto.driverType == DriverType.REGULAR
        reservationDto.carLicenseId == "EPA234"

        when: "driver stops park meter and ask for price of reservation"

        ResultActions stopReservation = mockMvc
            .perform(put("/parking/driver/stop").param("id", "1"))
            .andExpect(status().isOk())

        String dispendedCostResponse = mockMvc.perform(get("/parking/driver/cost").param("id", "1")).andReturn().response.contentAsString

        then: "he gets response with cost of reservation"

        BigDecimal cost = mapper.readValue(dispendedCostResponse, BigDecimal.class)
        cost == 2.0
    }

    def "should show valid path for operator"()
    {
        given: "there are two cars parked, one started park meter and the other didn't"
        reservationRepository.save(reservationWithCar)
        reservationRepository.save(notStartedReservationWithCar)

        when: "operator checks car which didn't start park meter"
        ResultActions stopReservation = mockMvc.perform(get("/parking/operator/control").param("licenseId", "EPA213"))

        then: "he gets successful response with status"
        def controlCarResponse = mapper.readValue(stopReservation.andReturn().response.contentAsString, boolean)
        !controlCarResponse
    }

    def "should show valid path for owner"()
    {
        given: "there are 3 ended on that day (vip, regular, regular) reservations"
        reservationRepository.save(reservationWithCarInt)

        when: "owner checks daily takings"
        ResultActions stopReservation = mockMvc.perform(get("/parking/owner/earnings").param("specificDay", "01-01-2011"))

        then: "he gets response with earned money on that day"
        def dailyTakings = mapper.readValue(stopReservation.andReturn().response.contentAsString, BigDecimal.class)
        dailyTakings == 3.0
    }
}
