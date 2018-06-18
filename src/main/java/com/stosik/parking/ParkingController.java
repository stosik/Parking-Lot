package com.stosik.parking;

import com.stosik.parking.reservation.domain.ReservationFacade;
import com.stosik.parking.reservation.dto.CreateReservationCommand;
import com.stosik.parking.reservation.dto.ReservationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
class ParkingController
{
    private final ReservationFacade reservationFacade;
    
    @PostMapping(value = "/driver/start")
    public ReservationDto startParkmeter(@RequestBody CreateReservationCommand createReservationCommand)
    {
        return reservationFacade.startParkmeter(createReservationCommand);
    }
    
    @PutMapping("/driver/stop")
    public ReservationDto stopParkmeter(@RequestParam Long id)
    {
        return reservationFacade.stopParkmeter(id);
    }
    
    @GetMapping("/driver/cost")
    public BigDecimal dispendReservationCost(@RequestParam Long id)
    {
        return reservationFacade.dispendReservationTicket(id);
    }
    
    @GetMapping("/operator/cars")
    public boolean checkIfParkmeterStarted(@RequestParam String licenseId)
    {
        return reservationFacade.checkVehicle(licenseId);
    }
    
    @GetMapping("/owner/earnings")
    public BigDecimal retrieveDailyTakings(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date specificDay)
    {
        return reservationFacade.dailyTakings(specificDay);
    }
}
