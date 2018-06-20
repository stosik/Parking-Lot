package com.stosik.parking.reservation.exceptions;

public class ReservationNotFoundException extends RuntimeException
{
    public ReservationNotFoundException(String id)
    {
        super("No reservation of id " + id + " found", null, false, false);
    }
}

