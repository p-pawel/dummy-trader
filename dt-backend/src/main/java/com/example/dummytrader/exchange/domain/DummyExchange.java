package com.example.dummytrader.exchange.domain;

import com.example.dummytrader.exchange.api.Exchange;
import com.example.dummytrader.exchange.api.PriceData;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
class DummyExchange implements Exchange {


    public static final BigDecimal DUMMY_PRICE_FROM = new BigDecimal("45000.00");
    public static final BigDecimal DUMMY_PRICE_TO = new BigDecimal("50000.00");

    @Override
    public PriceData getCurrentPrice() {
        return new PriceData(RandomBigDecimal.fromRange(DUMMY_PRICE_FROM, DUMMY_PRICE_TO));
    }
}
