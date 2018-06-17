package com.stosik.parking.reservation.exceptions;

public class ReservationNotFoundException extends RuntimeException
{
    public ReservationNotFoundException(Long id)
    {
        super("No reservation of id " + id + " found", null, false, false);
    }
}

