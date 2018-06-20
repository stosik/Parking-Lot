package com.stosik.parking.reservation.domain

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
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

        mapper.registerModule(new JavaTimeModule())
        def content = mapper.writeValueAsString(createReservationCommand)

        when: "driver starts park meter"

        ResultActions startReservationResponse = mockMvc
            .perform(post("/parking/driver/start").contentType(contentType).content(content))
            .andExpect(status().isOk())

        then: "there is one reservation in system"

        ReservationDto reservationStartDto = mapper.readValue(startReservationResponse.andReturn().response.contentAsString, ReservationDto.class)
        reservationStartDto.driverType == DriverType.REGULAR
        reservationStartDto.carLicenseId == "EPA234"

        when: "driver stops park meter and ask for price of reservation"

        ResultActions reservationStopResponse = mockMvc
            .perform(put("/parking/driver/stop").param("licenseId", "${reservationStartDto.carLicenseId}"))
            .andExpect(status().isOk())

        ReservationDto reservationStopDto = mapper.readValue(reservationStopResponse.andReturn().response.contentAsString, ReservationDto.class)

        String dispendedCostResponse = mockMvc
            .perform(get("/parking/driver/cost").param("reservationId", "${reservationStopDto.id}"))
            .andReturn().response.contentAsString

        then: "he gets response with cost of reservation"

        BigDecimal cost = mapper.readValue(dispendedCostResponse, BigDecimal.class)
        cost == 1.0
    }

    def "should show valid path for operator"()
    {
        given: "there are two cars parked, one started park meter and the other didn't"

        reservationRepository.save(nonParkedReservation)
        reservationRepository.save(parkedReservation)

        when: "operator checks car which didn't start park meter"

        ResultActions stopReservation = mockMvc.perform(get("/parking/operator/control").param("licenseId", "EPA213"))

        then: "he gets successful response with status"

        def controlCarResponse = mapper.readValue(stopReservation.andReturn().response.contentAsString, boolean)
        !controlCarResponse
    }

    def "should show valid path for owner"()
    {
        given: "there are 3 ended on that day (vip, regular, regular) reservations"

        reservationRepository.save(endedReservationWithCar)
        reservationRepository.save(secondEndedReservation)
        reservationRepository.save(regularEndedReservation)

        when: "owner checks daily takings"

        ResultActions stopReservation = mockMvc.perform(get("/parking/owner/earnings").param("specificDay", "01-01-2011"))

        then: "he gets response with earned money on that day"

        def dailyTakings = mapper.readValue(stopReservation.andReturn().response.contentAsString, BigDecimal.class)
        dailyTakings == 15.0
    }
}
