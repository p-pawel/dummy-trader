package com.example.dummytrader.exchange.api;

import com.example.dummytrader.web.model.PlaceOrderRequest;
import com.example.dummytrader.web.model.PlaceOrderResult;
import reactor.core.publisher.Sinks;

public interface Exchange {
    Sinks.Many<PriceData> getPrice();

    Sinks.Many<BalanceData> getBalance();

    PriceData getCurrentPrice();

    BalanceData getCurrentBalance();

    PlaceOrderResult placeMarketOrder(PlaceOrderRequest request);

    void emitBalance();
}
