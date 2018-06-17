package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.model.Reservation;

import java.math.BigDecimal;

interface RateEvaluatorStrategy
{
    BigDecimal calculateReservationCost(Reservation reservation);
}
