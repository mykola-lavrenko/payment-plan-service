package com.mlavrenko.api.services.calculators.formatters;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public interface MoneyFormatter {
    BigDecimal format(@NotNull BigDecimal value);
}
