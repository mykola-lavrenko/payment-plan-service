package com.mlavrenko.api.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class LoanDetails {
    @NotNull
    @Positive(message = "loan amount should be a positive value")
    private BigDecimal loanAmount;
    @NotNull
    @Positive(message = "rate should be a positive value")
    private BigDecimal nominalRate;
    @Positive(message = "amount of months should be a positive value")
    private int duration;
    @NotNull
    private ZonedDateTime startDate;

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public BigDecimal getNominalRate() {
        return nominalRate;
    }

    public void setNominalRate(BigDecimal nominalRate) {
        this.nominalRate = nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "LoanDetails{" +
                "loanAmount=" + loanAmount +
                ", nominalRate=" + nominalRate +
                ", duration=" + duration +
                ", startDate=" + startDate +
                '}';
    }
}
