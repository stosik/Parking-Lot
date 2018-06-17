package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.PriceCalculator;
import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.parking.reservation.dto.CreateReservationCommand;
import com.stosik.parking.reservation.dto.ReservationDto;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Transactional
public class ReservationFacade
{
    private final ReservationRepository reservationRepository;
    private final CarRepository carRepository;
    private final PriceCalculator priceCalculator;
    private final Meter parkingMeter;
    private final ReservationDtoCreator reservationDtoCreator;
    
    ReservationFacade(ReservationRepository reservationRepository, CarRepository carRepository, PriceCalculator priceCalculator,
        Meter parkingMeter, ReservationDtoCreator reservationDtoCreator)
    {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.priceCalculator = priceCalculator;
        this.reservationDtoCreator = reservationDtoCreator;
        this.parkingMeter = parkingMeter;
    }
    
    public ReservationDto startParkmeter(CreateReservationCommand createReservationCommand)
    {
        Reservation reservation = parkingMeter.startReservation(createReservationCommand);
        reservation = reservationRepository.save(reservation);
        
        return reservationDtoCreator.from(reservation);
    }
    
    public ReservationDto stopParkmeter(Long id)
    {
        Reservation reservation = reservationRepository.findById(id);
        reservation = parkingMeter.stopReservation(reservation);
        reservation.setCost(priceCalculator.calculatePrice(reservation));
        
        return reservationDtoCreator.from(reservation);
    }
    
    public BigDecimal dispendReservationTicket(Long id)
    {
        return reservationRepository
            .findById(id)
            .getCost();
    }
    
    public boolean checkVehicle(String licenseId)
    {
        Reservation reservation = carRepository
            .findByLicenseId(licenseId)
            .getReservation();
        
        return hasStartedParkmeter(reservation);
    }
    
    public BigDecimal dailyTakings(Pageable pageable, Date day)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(day);
        
        return reservationRepository
            .findByDate(pageable, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR))
            .stream()
            .map(Reservation::getCost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    private boolean hasStartedParkmeter(Reservation reservation)
    {
        return reservation.getStartTime() != null;
    }
}
