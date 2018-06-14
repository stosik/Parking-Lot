package com.stosik.parking.domain;

import com.stosik.parking.domain.evaluator.Evaluator;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Set;

@Transactional
public class ReservationFacade
{
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final Set<Evaluator> evaluators;
    private final Meter parkingMeter;
    
    ReservationFacade(ReservationRepository reservationRepository, CarRepository carRepository, Set<Evaluator> evaluators, Meter parkingMeter)
    {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.evaluators = evaluators;
        this.parkingMeter = parkingMeter;
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
    
    }
    
    public double dailyTakings(Pageable pageable, Date day)
    {
        return reservationRepository
            .findWithParticularDayAndMonth(pageable, day)
            .stream()
            .map(this::calculateCost)
            .reduce(0.0, Double::sum);
    }
    
    public double dispendReservationTicket()
    {
        return 0.0;
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
    
    /*
        TODO maybe supplied with visitor pattern for Evaluators
     */
    private double calculateCost(Reservation reservation)
    {
        return evaluators
            .stream()
            .filter(evaluator -> evaluator.isAppropriateFor(reservation.getDriver().getType()))
            .map(evaluator -> evaluator.calculateReservationCost(reservation))
            .mapToDouble(Double::doubleValue)
            .sum();
    }
    
    private boolean hasOnlyStartDate(Reservation reservation)
    {
        return reservation.getStartTime() != null && reservation.getStopTime() == null;
    }
}
