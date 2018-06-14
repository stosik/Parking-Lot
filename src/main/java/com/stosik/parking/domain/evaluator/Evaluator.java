package com.stosik.parking.domain.evaluator;

import com.stosik.parking.domain.Reservation;

import java.util.Date;

public interface Evaluator
{
    double calculateReservationCost(Reservation reservation);
}
