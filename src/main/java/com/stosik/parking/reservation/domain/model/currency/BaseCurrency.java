package com.stosik.parking.reservation.domain.model.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class BaseCurrency
{
    String symbol;
    
    double ratio;
}
