package com.stosik.parking.reservation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cars")
public class Car
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    
    String brand;
    
    String model;
    
    @OneToOne(fetch = FetchType.LAZY)
    Reservation reservation;
}
