package com.stosik.parking;

import com.stosik.parking.domain.Driver;
import com.stosik.parking.domain.Reservation;
import com.stosik.parking.domain.ReservationFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/parking")
public class ParkingController
{
    private ReservationFacade reservationFacade;
    
    @GetMapping("/owner/earnings")
    public double retrieveDailyTakings(Pageable pageable, @RequestParam Date specificDay)
    {
        return reservationFacade.dailyTakings(pageable, specificDay);
    }
    
    @GetMapping("/driver/reservation")
    public double rentalCost()
    {
        return reservationFacade.dispendReservationTicket();
    }
    
    @GetMapping("/operator/car/{id}")
    public boolean checkIfParkmeterStarted(Long id)
    {
        return reservationFacade.checkVehicle(id);
    }
    
    @PostMapping("/driver/start")
    public Reservation startParkmeter(Driver driver)
    {
       return reservationFacade.startParkmeter(driver);
    }
    
    @PostMapping("/driver/stop")
    public void stopParkmeter(Reservation reservation)
    {
        reservationFacade.stopParkmeter(reservation);
    }
}
