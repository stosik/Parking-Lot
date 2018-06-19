package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.evaluator.PriceCalculator;
import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.parking.reservation.dto.CreateReservationCommand;
import com.stosik.parking.reservation.dto.ReservationDto;
import com.stosik.parking.reservation.exceptions.ReservationNotFoundException;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Slf4j
public class ReservationFacade
{
    private final ReservationRepository reservationRepository;
    private final PriceCalculator priceCalculator;
    private final Meter parkingMeter;
    private final ParkingStore parkingStore;
    private final ReservationDtoCreator reservationDtoCreator;
    
    ReservationFacade(ReservationRepository reservationRepository, ParkingStore parkingStore, PriceCalculator priceCalculator,
        Meter parkingMeter, ReservationDtoCreator reservationDtoCreator)
    {
        this.reservationRepository = reservationRepository;
        this.parkingStore = parkingStore;
        this.priceCalculator = priceCalculator;
        this.parkingMeter = parkingMeter;
        this.reservationDtoCreator = reservationDtoCreator;
    }
    
    public ReservationDto startParkmeter(CreateReservationCommand createReservationCommand)
    {
        return Optional
            .ofNullable(createReservationCommand)
            .map(parkingMeter::startReservation)
            .map(parkingStore::save)
            .map(reservationDtoCreator::from)
            .orElse(ReservationDto.builder().build());
    }
    
    public ReservationDto stopParkmeter(String licenseId)
    {
        return parkingStore
            .findById(licenseId)
            .map(parkingMeter::stopReservation)
            .map(this::calculateCost)
            .map(reservationRepository::save)
            .map(reservationDtoCreator::from)
            .orElseThrow(() -> new ReservationNotFoundException(licenseId));
    }
    
    public BigDecimal dispendReservationTicket(Long id)
    {
        return reservationRepository
            .findById(id)
            .map(Reservation::getCost)
            .orElseThrow(() -> new ReservationNotFoundException(String.valueOf(id)));
    }
    
    public boolean checkVehicle(String licenseId)
    {
        return parkingStore
            .findAll()
            .stream()
            .anyMatch(reservation -> hasSameLicensePlate(reservation, licenseId));
    }
    
    public BigDecimal dailyTakings(Date dateToCheck)
    {
        LocalDate specificDay = dateToCheck
            .toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate();
        
        return reservationRepository
            .findByDate(specificDay.getDayOfMonth(), specificDay.getMonthValue(), specificDay.getYear())
            .stream()
            .map(Reservation::getCost)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    private boolean hasStartedParkmeter(Reservation reservation)
    {
        return reservation.getStartTime() != null;
    }
    
    private Reservation calculateCost(Reservation reservation)
    {
        reservation.setCost(priceCalculator.calculatePrice(reservation));
        
        return reservation;
    }
    
    private boolean hasSameLicensePlate(Reservation reservation, String licenseId)
    {
        return Objects.equals(reservation.getCarLicenseId(), licenseId);
    }
}
