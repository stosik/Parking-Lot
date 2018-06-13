package com.stosik.parking.domain.evaluator

import com.stosik.parking.domain.Reservation
import org.apache.commons.lang3.time.DateUtils
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

class VipEvaluatorSpec extends Specification
{
    @Shared
    Date startTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:00:00")

    @Shared
    Date stopTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2011-01-01 00:55:00")

    Evaluator vipEvaluator = new VipEvaluator()
    Reservation reservation = Mock()

    void "should evaluate price only for first hour for vip driver"()
    {
        given:

        reservation.getStartTime() >> startTime
        reservation.getStopTime() >> stopTime

        when:

        def costOfReservation = vipEvaluator.calculateReservationCost(reservation)

        then:

        costOfReservation == 0.0
    }

    void "should evaluate price for first two hours for vip driver"()
    {
        given:

        reservation.getStartTime() >> startTime
        reservation.getStopTime() >> DateUtils.addHours(stopTime, 1)

        when:

        def costOfReservation = vipEvaluator.calculateReservationCost(reservation)

        then:

        costOfReservation == 2.0
    }

    void "should evaluate price for given hours for vip driver"()
    {
        given:

        reservation.getStartTime() >> startTime
        reservation.getStopTime() >> DateUtils.addHours(stopTime, 2)

        when:

        def costOfReservation = vipEvaluator.calculateReservationCost(reservation)

        then:

        costOfReservation == 4.4
    }
}
