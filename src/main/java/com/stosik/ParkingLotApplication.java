package com.stosik;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@Slf4j
@SpringBootApplication
public class ParkingLotApplication
{
    public static void main(String[] args)
    {
        new SpringApplicationBuilder(ParkingLotApplication.class)
            .bannerMode(Banner.Mode.OFF)
            .headless(true)
            .application()
            .run(args);
    
        log.info("\nhttp://localhost:8080/\n");
    }
}
