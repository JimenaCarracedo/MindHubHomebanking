package com.mindhub.homebanking.DTO;


import com.mindhub.homebanking.Models.*;
import com.mindhub.homebanking.Models.Client;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Loan> loans;
    private Set<Account> accounts;
    private int payments;
    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.accounts=client.getAccounts();

    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Set<Account> getAccountList() {
        return accounts;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }
}