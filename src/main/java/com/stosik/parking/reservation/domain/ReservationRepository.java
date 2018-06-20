package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface ReservationRepository extends JpaRepository<Reservation, Long>
{
    @Query("SELECT r FROM Reservation r WHERE DAY(r.stopTime) = :day and MONTH(r.stopTime) = :month and YEAR(r.stopTime) = :year")
    List<Reservation> findByDate(@Param("day") int day, @Param("month") int month, @Param("year") int year);
}
