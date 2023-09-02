package com.mindhub.homebanking.dtos;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationDTO {
    private long id;
    private double amount;
    private List<Integer> payments;
    private String loanType;
    private Set<ClientLoan> clientLoans;

    public ApplicationDTO() {
    }

    public ApplicationDTO(LoanDTO loan) {
        this.id = loan.getId();
        this.loanType = loan.getName();
        this.amount = loan.getAmount();
        this.payments = loan.getPayments();

    }

    public long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public Set<ClientLoan> getClientLoans() {
        return clientLoans;
    }


    public String getLoanType() {
        return loanType;
    }

}
