package com.stosik.parking.reservation.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

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
    @NonNull
    @Size(min = 6, max = 6)
    String licenseId;
    
    @OneToOne(mappedBy = "car")
    Reservation reservation;
}
