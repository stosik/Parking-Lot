package com.stosik.parking.reservation.domain.model.currency;

public class EUROCurrency extends BaseCurrency
{
    private EUROCurrency(String symbol, double ratio)
    {
        super("EUR", 0.23);
    }
}
