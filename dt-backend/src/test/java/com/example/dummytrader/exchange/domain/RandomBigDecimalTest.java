package com.example.dummytrader.exchange.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class RandomBigDecimalTest {

    @Test
    void shouldGenerateFromRange() {

        // given
        BigDecimal from = new BigDecimal("45000.00");
        BigDecimal to = new BigDecimal("50000.00");

        for (int i = 0; i < 1000; i++) {

            // when
            BigDecimal random = RandomBigDecimal.fromRange(from, to);

            // then
            Assertions.assertThat(random).isGreaterThanOrEqualTo(from);
            Assertions.assertThat(random).isLessThanOrEqualTo(to);
            Assertions.assertThat(random).hasScaleOf(from.scale());
        }
    }
}
