package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface CarRepository extends JpaRepository<Car, Long>
{
    Optional<Car> findByLicenseId(String licenseId);
}
