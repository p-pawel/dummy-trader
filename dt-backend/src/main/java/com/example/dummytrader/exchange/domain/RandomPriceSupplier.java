package com.example.dummytrader.exchange.domain;

import com.example.dummytrader.exchange.api.PriceData;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;

@Component
class RandomPriceSupplier implements PriceSupplier {

    public static final BigDecimal DUMMY_PRICE_FROM = new BigDecimal("45000.00");
    public static final BigDecimal DUMMY_PRICE_TO = new BigDecimal("50000.00");

    public static final Flux<PriceData> PRICE_SUPPLIER = Flux.interval(Duration.ofSeconds(1))
            .map(e -> new PriceData(RandomBigDecimal.fromRange(DUMMY_PRICE_FROM, DUMMY_PRICE_TO)));

    @Override
    public Flux<PriceData> getPriceSupplier() {
        return PRICE_SUPPLIER;
    }

}
