package com.stosik.parking.reservation.exceptions;

public class CreateReservationException extends RuntimeException
{
    public CreateReservationException()
    {
        super("Could not create reservation for the given command", null, false, false);
    }
}
