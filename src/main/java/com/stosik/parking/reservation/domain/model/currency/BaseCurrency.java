package com.stosik.parking.reservation.domain.model.currency;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseCurrency
{
    String symbol;
    
    double ratio;
}
