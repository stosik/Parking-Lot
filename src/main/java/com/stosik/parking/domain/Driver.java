package com.stosik.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "reservation")
@Table(name = "drivers")
class Driver
{
    @Id
    private Long id;
    
    @Enumerated
    private DriverType type;
    
    @NotNull
    @Column(nullable = false)
    private String firstName;
    
    @NotNull
    @Column(nullable = false)
    private String lastName;
    
    @OneToOne
    private Reservation reservation;
}
