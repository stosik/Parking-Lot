package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CarRepository extends JpaRepository<Car, String>
{
    Optional<Car> findByLicenseId(String licenseId);
    
    void deleteByLicenseId(String licenseId);
}
