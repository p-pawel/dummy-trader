package com.example.dummytrader.web;

import com.example.dummytrader.exchange.api.Exchange;
import com.example.dummytrader.exchange.api.PriceData;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin
@RestController
@RequestMapping("/price")
class PriceController {

    private final Exchange exchange;

    public PriceController(Exchange exchange) {
        this.exchange = exchange;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PriceData> getStream() {
        return exchange.getPrice().asFlux();
    }

}
