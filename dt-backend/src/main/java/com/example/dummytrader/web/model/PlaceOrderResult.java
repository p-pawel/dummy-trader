package com.example.dummytrader.web.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlaceOrderResult(LocalDateTime createdAt, BigDecimal price, String error) {
}
