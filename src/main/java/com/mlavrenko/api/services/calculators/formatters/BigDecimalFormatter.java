package com.mlavrenko.api.services.calculators.formatters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@Component
public class BigDecimalFormatter implements MoneyFormatter {
    @Value("${money.scale}")
    private int scale;

    @Override
    public BigDecimal format(@NotNull BigDecimal value) {
        return Objects.requireNonNull(value).setScale(scale, RoundingMode.HALF_EVEN).stripTrailingZeros();
    }
}
