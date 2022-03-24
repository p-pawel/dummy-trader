package com.example.dummytrader.exchange.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomBigDecimal {

    private RandomBigDecimal() {
        // static util class
    }

    public static BigDecimal fromRange(BigDecimal from, BigDecimal to) {

        if (to.compareTo(from) < 0) {
            throw new IllegalArgumentException("Cannot generate numer. TO should be greater than or equal to FROM");
        }

        if (to.scale() != from.scale()) {
            throw new IllegalArgumentException("Cannot generate numer. FROM and TO have different scales");
        }

        BigDecimal range = to.subtract(from);
        return from.add(range.multiply(BigDecimal.valueOf(Math.random()))).setScale(from.scale(), RoundingMode.HALF_UP);
    }
}
