package com.mlavrenko.api.services.calculators;

import com.mlavrenko.api.domain.LoanDetails;
import com.mlavrenko.api.domain.PaymentDetails;
import com.mlavrenko.api.domain.PaymentDetailsBuilder;
import com.mlavrenko.api.services.calculators.formatters.MoneyFormatter;
import com.mlavrenko.api.utils.FinancialUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;

@Component
public class MonthlyPaymentsCalculator implements PaymentsCalculator {
    private final MoneyFormatter formatter;

    public MonthlyPaymentsCalculator(MoneyFormatter formatter) {
        this.formatter = formatter;
    }

    public List<PaymentDetails> calculatePayments(LoanDetails loanDetails) {
        int periods = loanDetails.getDuration();
        var startDate = loanDetails.getStartDate();
        var initialOutstandingPrincipal = loanDetails.getLoanAmount();
        var rate = getDecimalMonthlyRate(loanDetails.getNominalRate());
        var annuityPayment = formatter.format(FinancialUtils.calculateAnnuityPayment(initialOutstandingPrincipal, rate, periods));

        var borrowerPayments = new LinkedList<PaymentDetails>();
        for (int i = 0; i < periods; i++) {
            var interest = formatter.format(FinancialUtils.calculateInterest(rate, initialOutstandingPrincipal));

            var borrowerPaymentAmount = annuityPayment;
            var principal = formatter.format(borrowerPaymentAmount.subtract(interest));

            if (annuityPayment.compareTo(initialOutstandingPrincipal) > 0) {
                principal = initialOutstandingPrincipal;
                borrowerPaymentAmount = formatter.format(initialOutstandingPrincipal.add(interest));
            }

            var remainingOutstandingPrincipal = formatter.format(initialOutstandingPrincipal.subtract(principal));

            var borrowerPayment = PaymentDetailsBuilder.create()
                    .setBorrowerPaymentAmount(borrowerPaymentAmount)
                    .setDate(startDate.plusMonths(i))
                    .setInitialOutstandingPrincipal(initialOutstandingPrincipal)
                    .setInterest(interest)
                    .setPrincipal(principal)
                    .setRemainingOutstandingPrincipal(remainingOutstandingPrincipal)
                    .createPeriodPaymentDetails();

            borrowerPayments.add(borrowerPayment);
            initialOutstandingPrincipal = remainingOutstandingPrincipal;
        }
        return borrowerPayments;
    }

    /**
     * Assumption from the task description (year has 360 days and month has 30 days),
     * gives a possibility to calculate monthly rate from annual rate by simple division by month count in a year.
     * Also percentage format is converted to decimal format.
     */
    private BigDecimal getDecimalMonthlyRate(BigDecimal nominalRate) {
        var monthsInAYear = 12;
        var decimalBase = 100;
        return nominalRate.divide(BigDecimal.valueOf(decimalBase * monthsInAYear), MathContext.DECIMAL128);
    }
}
