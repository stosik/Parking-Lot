package com.stosik.parking;

import com.stosik.parking.domain.ReservationFacade;
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
    public double retrieveDailyTakings(@RequestParam Date specificDay)
    {
        return reservationFacade.dailyTakings(specificDay);
    }
    
    @GetMapping("/driver/reservation")
    public double rentalCost()
    {
        return reservationFacade.dispendReservationTicket();
    }
    
    @GetMapping("/operator/car/{id}")
    public boolean checkIfParkmeterStarted()
    {
        return reservationFacade.checkVehicle();
    }
    
    @PostMapping("/driver/start")
    public void startParkmeter()
    {
        reservationFacade.startParkmeter();
    }
    
    @PostMapping("/driver/stop")
    public void stopParkmeter()
    {
        reservationFacade.stopParkmeter();
    }
}
