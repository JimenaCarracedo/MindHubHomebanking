package com.mindhub.homebanking.dtos;

import java.util.List;

public class LoanApplicationDTO {
    private Double amount;
    private Integer payments;
    private long loanId;
    private String toAccountNumber;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(Double amount, Integer payments, long loanId, String toAccountNumber ) {
        this.loanId = loanId;
        this.amount = amount;
        this.payments = payments;
        this.toAccountNumber = toAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Integer getPayments() {
        return payments;
    }

    public long getLoanId() {
        return loanId;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }
}