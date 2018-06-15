package com.stosik.parking.reservation.domain;

import com.stosik.parking.reservation.dto.ReservationNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

interface ReservationRepository extends Repository<Reservation, Long>
{
    Reservation save(Reservation reservation);
    
    Reservation findById(Long id);
    
    @Query("select r from Reservation r where year(r.stopTime) = ?2 and month(r.stopTime) = ?3 and day(r.stopTime) = ?4")
    List<Reservation> findByDate(Pageable pageable, int year, int month, int day);
    
    Page<Reservation> findAll(Pageable pageable);
    
    default Reservation findOneOrThrow(Long id)
    {
        Reservation reservation = findById(id);
        if(reservation == null)
        {
            throw new ReservationNotFoundException(id);
        }
        return reservation;
    }
}
