package com.example.dummytrader.web;

import com.example.dummytrader.exchange.api.BalanceData;
import com.example.dummytrader.exchange.api.Exchange;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin
@RestController
@RequestMapping("/balance")
class BalanceController {

    private final Exchange exchange;

    public BalanceController(Exchange exchange) {
        this.exchange = exchange;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<BalanceData> getStream() {

        // probably an ugly way - to force emission of the value just after "subscribe" to the stream
        runWithDelay(100, exchange::emitBalance);

        return exchange.getBalance().asFlux();
    }

    private void runWithDelay(int delay, Runnable task) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
