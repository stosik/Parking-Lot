package com.stosik.parking.reservation.domain.evaluator;

import com.stosik.parking.reservation.domain.model.Reservation;

interface RateEvaluatorStrategy
{
    double calculateReservationCost(Reservation reservation);
}
