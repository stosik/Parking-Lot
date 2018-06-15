package com.stosik.parking.reservation.dto;

public class AlreadyExistingReservationException extends RuntimeException
{
    public AlreadyExistingReservationException(Long id)
    {
        super("There is already running reservation for " + id + " car", null, false, false);
    }
}
