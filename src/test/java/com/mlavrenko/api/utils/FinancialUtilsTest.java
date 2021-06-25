package com.mlavrenko.api.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FinancialUtilsTest {

    @ParameterizedTest
    @MethodSource("annuityPaymentValuesProvider")
    @DisplayName("calculateAnnuityPayment should compute annuity payment from given rate, init outstanding principal and period")
    void calculateAnnuityPayment(BigDecimal presentValue, BigDecimal rate, int periods, BigDecimal expected) {
        var annuityPayment = FinancialUtils.calculateAnnuityPayment(presentValue, rate, periods);
        assertThat(annuityPayment).isEqualByComparingTo(expected);

    }

    private static Stream<Arguments> annuityPaymentValuesProvider() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(1000), BigDecimal.valueOf(0.01), 1, BigDecimal.valueOf(1010)),
                Arguments.of(BigDecimal.valueOf(500.05), BigDecimal.valueOf(0.001), 10, BigDecimal.valueOf(50.28044))
        );
    }

    @ParameterizedTest
    @MethodSource("interestValuesProvider")
    @DisplayName("calculateInterest should compute interest from given rate and init outstanding principal")
    void calculateInterest(BigDecimal rate, BigDecimal initOutstandingPrincipal, BigDecimal expected) {
        var interest = FinancialUtils.calculateInterest(rate, initOutstandingPrincipal);

        assertThat(interest).isEqualByComparingTo(expected);
    }

    private static Stream<Arguments> interestValuesProvider() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(0.01), BigDecimal.valueOf(1000), BigDecimal.valueOf(10)),
                Arguments.of(BigDecimal.valueOf(0.001), BigDecimal.valueOf(1000.001), BigDecimal.valueOf(1.000001))
        );
    }
}