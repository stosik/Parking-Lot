package com.stosik.parking.domain;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
@RequiredArgsConstructor
public class ReservationFacade
{
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    
    public double dailyTakings(Date day)
    {
        return 0.0;
    }
    
    public double dispendReservationTicket()
    {
        return 0.0;
    }
    
    public void startParkmeter()
    {
        Reservation reservation = Reservation
            .builder()
            .id(1L)
            .startTime(new Date())
            .build();
        
        reservationRepository.save(reservation);
    }
    
    public void stopParkmeter(Reservation reservation)
    {
        Reservation detached = reservationRepository.findById(reservation.getId());
        detached.setStopTime(new Date());
        
        reservationRepository.save(detached);
    }
    
    public boolean checkVehicle(Long id)
    {
        return carRepository
            .findById(id)
            .getDriver()
            .getReservations()
            .stream()
            .anyMatch(this::hasOnlyStartDate);
    }
    
    private boolean hasOnlyStartDate(Reservation reservation)
    {
        return reservation.getStartTime() != null && reservation.getStopTime() == null;
    }
}
