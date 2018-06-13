package com.stosik.parking;

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
    @GetMapping("/owner/earnings")
    public void retrieveDailyTakings(@RequestParam Date specificDay)
    {
    
    }
    
    @GetMapping("/driver/reservation")
    public void checkHowMuchToPay()
    {
    
    }
    
    @GetMapping("/operator/car/{id}")
    public void checkIfStartedParkmeter()
    {
    
    }
    
    @PostMapping("/driver/start")
    public void startParkmeter()
    {
    
    }
    
    @PostMapping("/driver/stop")
    public void stopParkmeter()
    {
    
    }
}
