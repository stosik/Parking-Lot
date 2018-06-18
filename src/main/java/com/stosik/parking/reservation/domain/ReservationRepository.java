package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface ReservationRepository extends JpaRepository<Reservation, Long>
{
    @Query("select r from Reservation r where year(r.stopTime) = ?1 and month(r.stopTime) = ?2 and day(r.stopTime) = ?3")
    List<Reservation> findByDate(int year, int month, int day);
}
