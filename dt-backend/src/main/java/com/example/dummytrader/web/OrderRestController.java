package com.example.dummytrader.web;

import com.example.dummytrader.exchange.api.Exchange;
import com.example.dummytrader.web.model.PlaceOrderRequest;
import com.example.dummytrader.web.model.PlaceOrderResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/order")
class OrderRestController {

    private final Exchange exchange;

    public OrderRestController(Exchange exchange) {
        this.exchange = exchange;
    }

    @PostMapping
    public ResponseEntity<Mono<PlaceOrderResult>> placeMarketOrder(@RequestBody PlaceOrderRequest request) {
        PlaceOrderResult result = exchange.placeMarketOrder(request);

        HttpStatus status = result.error() == null ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY;

        return new ResponseEntity<>(Mono.just(result), status);
    }

}
