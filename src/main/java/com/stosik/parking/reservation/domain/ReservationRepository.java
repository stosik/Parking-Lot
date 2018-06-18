package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.domain.model.Reservation;
import com.stosik.parking.reservation.exceptions.ReservationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ReservationRepository extends JpaRepository<Reservation, Long>
{
    @Query("select r from Reservation r where year(r.stopTime) = ?2 and month(r.stopTime) = ?3 and day(r.stopTime) = ?4")
    List<Reservation> findByDate(Pageable pageable, int year, int month, int day);
}
