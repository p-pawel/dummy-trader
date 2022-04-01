package com.example.dummytrader.exchange.domain;

import com.example.dummytrader.exchange.api.PriceData;
import reactor.core.publisher.Flux;

public interface PriceSupplier {

    Flux<PriceData> getPriceSupplier();

}
