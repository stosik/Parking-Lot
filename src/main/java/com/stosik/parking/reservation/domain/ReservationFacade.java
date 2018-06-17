package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.PriceCalculator;
import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.parking.reservation.dto.CreateReservationCommand;
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
    
    public Reservation startParkmeter(CreateReservationCommand createReservationCommand)
    {
        Reservation reservation = parkingMeter.startReservation(createReservationCommand);
        
        return reservationRepository.save(reservation);
    }
    
    public Reservation stopParkmeter(Long id)
    {
        Reservation reservation = reservationRepository.findById(id);
        reservation = parkingMeter.stopReservation(reservation);
        reservation.setCost(priceCalculator.calculatePrice(reservation));
        
        return reservation;
    }
    
    public double dispendReservationTicket(Long id)
    {
        return reservationRepository
            .findById(id)
            .getCost();
    }
    
    public boolean checkVehicle(Long id)
    {
        Reservation reservation = carRepository
            .findById(id)
            .getReservation();
        
        return hasStartedParkmeter(reservation);
    }
    
    public double dailyTakings(Pageable pageable, Date day)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        
        return reservationRepository
            .findByDate(pageable, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
            .stream()
            .map(Reservation::getCost)
            .reduce(0.0, Double::sum);
    }
    
    public Page<Reservation> showAll(Pageable pageable)
    {
        return reservationRepository.findAll(pageable);
    }
    
    private boolean hasStartedParkmeter(Reservation reservation)
    {
        return reservation.getStartTime() != null;
    }
}
