package com.stosik.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "reservations")
@Table(name = "drivers")
public class Driver
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
    
    @OneToOne(mappedBy = "driver")
    private Car car;
    
    @Singular
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
}
