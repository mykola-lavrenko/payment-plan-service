package com.mlavrenko.api.services.calculators;

import com.mlavrenko.api.domain.LoanDetails;
import com.mlavrenko.api.domain.PaymentDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Monthly payments plan calculator test")
@SpringBootTest
class MonthlyPaymentsCalculatorTest {
    @Autowired
    private PaymentsCalculator monthlyPaymentCalculator;

    @Test
    @DisplayName("calculatePayments should return expected list of payments when loan details are valid")
    void calculatePayments() {
        var loanDetails = setUpLoanDetails();

        var payments = monthlyPaymentCalculator.calculatePayments(loanDetails);

        assertAll(
                () -> assertThat(payments).hasSize(loanDetails.getDuration()),
                () -> assertThatAnnuityPaymentExceptLastEqualsTo(new BigDecimal("219.36"), payments),
                () -> assertThatLastAnnuityPaymentEqualsTo(new BigDecimal("219.28"), payments),
                () -> assertThat(getTotalOf(PaymentDetails::getPrincipal, payments)).isEqualByComparingTo(loanDetails.getLoanAmount()),
                () -> assertThat(getTotalOf(PaymentDetails::getInterest, payments)).isEqualByComparingTo(BigDecimal.valueOf(264.56))
        );
    }

    private void assertThatAnnuityPaymentExceptLastEqualsTo(BigDecimal annuityPayment, List<PaymentDetails> payments) {
        var normalAnnuityPaymentsCount = payments.stream()
                .filter(payment -> isNotLastMonth(payment.getDate()) &&
                        annuityPayment.compareTo(payment.getBorrowerPaymentAmount()) == 0)
                .count();

        assertThat(normalAnnuityPaymentsCount).isEqualTo(payments.size() - 1);
    }

    private void assertThatLastAnnuityPaymentEqualsTo(BigDecimal annuityPayment, List<PaymentDetails> payments) {
        var lastAnnuityPayment = payments.stream()
                .filter(payment -> isLastMonth(payment.getDate()))
                .map(PaymentDetails::getBorrowerPaymentAmount)
                .findFirst()
                .orElse(BigDecimal.ZERO);

        assertThat(lastAnnuityPayment).isEqualByComparingTo(annuityPayment);
    }

    private boolean isLastMonth(ZonedDateTime date) {
        return getStartDate().plusMonths(23).equals(date);
    }

    private boolean isNotLastMonth(ZonedDateTime date) {
        return !isLastMonth(date);
    }

    private BigDecimal getTotalOf(Function<PaymentDetails, BigDecimal> mapper, List<PaymentDetails> payments) {
        return payments.stream().map(mapper).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private LoanDetails setUpLoanDetails() {
        var loanDetails = new LoanDetails();
        loanDetails.setStartDate(getStartDate());
        loanDetails.setDuration(24);
        loanDetails.setNominalRate(BigDecimal.valueOf(5));
        loanDetails.setLoanAmount(BigDecimal.valueOf(5000));
        return loanDetails;
    }

    private ZonedDateTime getStartDate() {
        return ZonedDateTime.parse("2018-01-01T00:00:00Z");
    }
}