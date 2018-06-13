package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.Reservation;

public interface Evaluator
{
    Double calculateReservationCost(Reservation reservation);
}
