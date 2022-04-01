package com.example.dummytrader.exchange.domain;

import com.example.dummytrader.exchange.api.BalanceData;
import com.example.dummytrader.exchange.api.Exchange;
import com.example.dummytrader.exchange.api.PriceData;
import com.example.dummytrader.web.model.PlaceOrderRequest;
import com.example.dummytrader.web.model.PlaceOrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
class DummyExchange implements Exchange {

    private final static BigDecimal INIT_BALANCE = new BigDecimal("10000.00");
    private final static int DECIMAL_PLACES = 2;

    private final Sinks.Many<PriceData> price = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<BalanceData> balance = Sinks.many().multicast().onBackpressureBuffer();

    private BalanceData currentBalance;
    private PriceData currentPrice;

    @Autowired
    DummyExchange(PriceSupplier priceSupplier) {
        this(priceSupplier, INIT_BALANCE);
    }

    DummyExchange(PriceSupplier priceSupplier, BigDecimal initialBalance) {
        this.price.asFlux().subscribe(e -> this.currentPrice = e);
        this.balance.asFlux().subscribe(e -> this.currentBalance = e);

        priceSupplier.getPriceSupplier().subscribe(t -> price.tryEmitNext(t));
        balance.tryEmitNext(new BalanceData(initialBalance));
    }

    @PostConstruct
    public void init() {
        balance.tryEmitNext(new BalanceData(INIT_BALANCE));
    }

    @Override
    public PriceData getCurrentPrice() {
        return currentPrice;
    }

    @Override
    public Sinks.Many<PriceData> getPrice() {
        return price;
    }

    @Override
    public synchronized PlaceOrderResult placeMarketOrder(PlaceOrderRequest request) {

        PriceData price = getCurrentPrice() != null
                ? getCurrentPrice()
                : this.price.asFlux().blockFirst(Duration.ofSeconds(2));

        BigDecimal total = request.amount().multiply(price.price()).setScale(DECIMAL_PLACES, RoundingMode.HALF_UP);
        BigDecimal existingBalance = currentBalance.balance();
        if (total.compareTo(existingBalance) <= 0) {

            BigDecimal newBalance = existingBalance.subtract(total);
            this.balance.tryEmitNext(new BalanceData(newBalance));
            return new PlaceOrderResult(
                    LocalDateTime.now(),
                    price.price(),
                    null
            );
        } else {
            return new PlaceOrderResult(
                    null,
                    price.price(),
                    "Insufficient balance"
            );
        }
    }

    @Override
    public void emitBalance() {
        Sinks.EmitResult res = this.balance.tryEmitNext(new BalanceData(currentBalance.balance()));
    }

    @Override
    public BalanceData getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public Sinks.Many<BalanceData> getBalance() {
        return balance;
    }
}
