package com.stosik.parking.domain.evaluator

import com.stosik.parking.domain.Reservation
import org.apache.commons.lang3.time.DateUtils
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class RegularEvaluatorSpec extends Specification
{
    @Shared
    Date startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:00:00")

    @Shared
    Date stopTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00")

    Evaluator regularEvaluator = new RegularEvaluator()
    Reservation reservation = Mock()

    void "should evaluate price only for first hour for regular driver"()
    {
        given:

        reservation.getStartTime() >> startTime
        reservation.getStopTime() >> stopTime

        when:

        def costOfReservation = regularEvaluator.calculateReservationCost(reservation)

        then:

        costOfReservation == 1.0d
    }

    void "should evaluate price for first two hours for regular driver"()
    {
        given:

        reservation.getStartTime() >> startTime
        reservation.getStopTime() >> DateUtils.addHours(stopTime, 1)

        when:

        def costOfReservation = regularEvaluator.calculateReservationCost(reservation)

        then:

        costOfReservation == 3.0d
    }

    void "should evaluate price for 2+ hours for regular driver"()
    {
        given:

        reservation.getStartTime() >> startTime
        reservation.getStopTime() >> DateUtils.addHours(stopTime, 2)

        when:

        def costOfReservation = regularEvaluator.calculateReservationCost(reservation)

        then:

        costOfReservation == 6.0d
    }
}
