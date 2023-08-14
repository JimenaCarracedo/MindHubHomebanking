package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Models.ClientLoan;
import com.mindhub.homebanking.Models.Loan;

import java.util.List;
import java.util.stream.Collectors;

public class ClientLoanDTO {
    private long id;
    private long client_id;
    private long loan_id;
    private String name;
    private double amount;
    private List<Integer> payment;

    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan client) {

        this.loan_id = client.getLoan().getId();
        this.name = client.getLoan().getName();
        this.amount =client.getAmount();
        this.payment = client.getPayment();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getLoan_id() {
        return loan_id;
    }

    public void setLoan_id(long loan_id) {
        this.loan_id = loan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Integer> getPayment() {
        return payment;
    }

    public void setPayment(List<Integer> payment) {
        this.payment = payment;
    }
}
