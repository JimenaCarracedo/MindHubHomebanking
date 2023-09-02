package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Loan;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LoanDTO {
    private long id;
    private String name;
    private double maxAmount;

    private double amount;
    private List<Integer> payments;
    private Account accounts;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id =loan.getId();
        this.name = loan.getName();
        this.maxAmount = loan.getMaxAmount();
        this.amount = loan.getAmount();
        this.payments = loan.getPayments();
        this.accounts = loan.getAccounts();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public double getAmount() {
        return amount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Account getAccounts() {
        return accounts;
    }
}
