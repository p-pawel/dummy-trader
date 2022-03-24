package com.example.dummytrader.web;

import com.example.dummytrader.exchange.api.Exchange;
import com.example.dummytrader.exchange.api.PriceData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@CrossOrigin
@RestController
@RequestMapping("/price")
class PriceRestController {

    private final Exchange exchange;

    public PriceRestController(Exchange exchange) {
        this.exchange = exchange;
    }

    @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PriceData> streamFlux1() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(e -> exchange.getCurrentPrice());
    }

}
