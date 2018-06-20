package com.stosik.parking.reservation.domain.model.currency;

public class PLNCurrency extends BaseCurrency
{
    private PLNCurrency(String symbol, double ratio)
    {
        super("PLN", 1.0);
    }
}
