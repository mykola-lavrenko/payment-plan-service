package com.mlavrenko.api.services.calculators.formatters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("BigDecimal formatter test")
class BigDecimalFormatterTest {
    @Autowired
    private BigDecimalFormatter formatter;

    @ParameterizedTest
    @MethodSource("valuesProvider")
    @DisplayName("format should return new value with expected scale and without trailing zeros")
    void format(BigDecimal value, BigDecimal expected) {
        var formatted = formatter.format(value);
        assertThat(formatted).isEqualByComparingTo(expected);
    }

    private static Stream<Arguments> valuesProvider() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(12.12345), new BigDecimal("12.12")),
                Arguments.of(BigDecimal.valueOf(44.44444), new BigDecimal("44.44")),
                Arguments.of(BigDecimal.valueOf(55.55555), new BigDecimal("55.56")),
                Arguments.of(BigDecimal.valueOf(99.99999), new BigDecimal("100")),
                Arguments.of(BigDecimal.valueOf(11.00111), new BigDecimal("11")),
                Arguments.of(BigDecimal.valueOf(11.10111), new BigDecimal("11.1"))
        );
    }
}