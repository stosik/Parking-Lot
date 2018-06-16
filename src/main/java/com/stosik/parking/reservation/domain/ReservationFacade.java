package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.PriceCalculator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;

@Transactional
public class ReservationFacade
{
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final PriceCalculator priceCalculator;
    private final Meter parkingMeter;
    
    ReservationFacade(ReservationRepository reservationRepository, CarRepository carRepository, PriceCalculator priceCalculator, Meter parkingMeter)
    {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.priceCalculator = priceCalculator;
        this.parkingMeter = parkingMeter;
    }
    
    public Reservation startParkmeter(Driver driver)
    {
        Reservation reservation = parkingMeter.startReservation(driver);
        
        return reservationRepository.save(reservation);
    }
    
    public Reservation stopParkmeter(Long id)
    {
        return parkingMeter.stopReservation(reservationRepository.findById(id));
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
    
    public double dispendReservationTicket(Long id)
    {
        return reservationRepository
            .findById(id)
            .getCost();
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
    
    private double calculateCost(Reservation reservation)
    {
        return priceCalculator.calculatePrice(reservation);
    }
    
    private boolean hasOnlyStartDate(Reservation reservation)
    {
        return reservation.getStartTime() != null && reservation.getStopTime() == null;
    }
}
