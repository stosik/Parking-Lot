package com.stosik.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Reservation
{
    @Id
    private Long id;
    
    @Temporal(TemporalType.TIME)
    @CreatedDate
    private Date startTime;
    
    @Temporal(TemporalType.TIME)
    @CreatedDate
    private Date stopTime;
    
    private Double cost;
}
