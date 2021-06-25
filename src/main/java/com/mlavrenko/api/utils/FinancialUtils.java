package com.mlavrenko.api.utils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FinancialUtils {

    private FinancialUtils() {
        throw new IllegalStateException("Utils class shouldn't be instantiated!");
    }

    public static BigDecimal calculateAnnuityPayment(@NotNull BigDecimal presentValue, @NotNull BigDecimal rate, int periods) {
        var pow = rate.add(BigDecimal.ONE).pow(-periods, MathContext.DECIMAL128);
        return rate.multiply(presentValue).divide(BigDecimal.ONE.subtract(pow), RoundingMode.HALF_EVEN);
    }

    public static BigDecimal calculateInterest(@NotNull BigDecimal rate, @NotNull BigDecimal initOutstandingPrincipal) {
        return rate.multiply(initOutstandingPrincipal);
    }
}
