package com.stosik.parking.domain;

import com.stosik.parking.domain.evaluator.Evaluator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Calendar;
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
    
    public Reservation startParkmeter(Driver driver)
    {
        Reservation reservation = parkingMeter.startReservation(driver);
        
        return reservationRepository.save(reservation);
    }
    
    public void stopParkmeter(Reservation reservation)
    {
    
    }
    
    public double dailyTakings(Pageable pageable, Date day)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        
        return reservationRepository
            .findByDate(pageable, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
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
    
    public Page<Reservation> showAll(Pageable pageable)
    {
        return reservationRepository.findAll(pageable);
    }
    
    /*
        TODO maybe supplied with visitor pattern for Evaluators
     */
    private double calculateCost(Reservation reservation)
    {
        DriverType driverType = reservation.getDriver().getType();
        
        return evaluators
            .stream()
            .filter(evaluator -> evaluator.isAppropriateFor(driverType))
            .map(evaluator -> evaluator.calculateReservationCost(reservation))
            .mapToDouble(Double::doubleValue)
            .sum();
    }
    
    private boolean hasOnlyStartDate(Reservation reservation)
    {
        return reservation.getStartTime() != null && reservation.getStopTime() == null;
    }
}
