package com.example.dummytrader.exchange.domain;

import com.example.dummytrader.exchange.api.PriceData;
import com.example.dummytrader.web.model.PlaceOrderRequest;
import com.example.dummytrader.web.model.PlaceOrderResult;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class DummyExchangeTest {


    class MockPriceSupplier implements PriceSupplier {

        private final BigDecimal mockPrice;

        public MockPriceSupplier(BigDecimal mockPrice) {
            this.mockPrice = mockPrice;
        }

        @Override
        public Flux<PriceData> getPriceSupplier() {
            return Flux.interval(Duration.ofSeconds(1))
                    .map(e -> new PriceData(mockPrice));
        }
    }

    @Test
    void shouldPlaceMarketOrder() {

        // given
        PriceSupplier mockPriceSupplier = new MockPriceSupplier(new BigDecimal("50000.00"));
        BigDecimal balance = new BigDecimal("1000.00");

        DummyExchange exchange = new DummyExchange(mockPriceSupplier, balance);


        // when
        PlaceOrderRequest request = new PlaceOrderRequest(new BigDecimal("0.002"));
        PlaceOrderResult result = exchange.placeMarketOrder(request);

        // then
        assertThat(result.error()).isNull();
        assertThat(exchange.getCurrentBalance().balance()).isEqualByComparingTo("900"); // 1000 - 100
    }

    @Test
    void shouldPlaceMarketOrder_round() {

        // given
        PriceSupplier mockPriceSupplier = new MockPriceSupplier(new BigDecimal("45406.68"));
        BigDecimal balance = new BigDecimal("10000.00");

        DummyExchange exchange = new DummyExchange(mockPriceSupplier, balance);


        // when
        PlaceOrderRequest request = new PlaceOrderRequest(new BigDecimal("0.0021"));
        PlaceOrderResult result = exchange.placeMarketOrder(request);

        // then
        assertThat(result.error()).isNull();
        assertThat(exchange.getCurrentBalance().balance()).isEqualByComparingTo("9904.65"); // 1000 - 100
    }

    @Test
    void shouldNotPlaceMarketOrder() {

        // given
        PriceSupplier mockPriceSupplier = new MockPriceSupplier(new BigDecimal("50000.00"));
        BigDecimal balance = new BigDecimal("1000.00");

        DummyExchange exchange = new DummyExchange(mockPriceSupplier, balance);


        // when
        PlaceOrderRequest request = new PlaceOrderRequest(new BigDecimal("0.25"));
        PlaceOrderResult result = exchange.placeMarketOrder(request);

        // then
        assertThat(result.error()).isNotBlank();
        assertThat(exchange.getCurrentBalance().balance()).isEqualByComparingTo("1000"); // nothing was traded

    }

    @Test
    void shouldGetInitialBalance() {

        // given
        PriceSupplier mockPriceSupplier = new MockPriceSupplier(new BigDecimal("50000.00"));
        BigDecimal balance = new BigDecimal("1000.00");

        // when
        DummyExchange exchange = new DummyExchange(mockPriceSupplier, balance);

        // then
        assertThat(exchange.getCurrentBalance().balance()).isEqualByComparingTo("1000"); // nothing was traded

    }

}
