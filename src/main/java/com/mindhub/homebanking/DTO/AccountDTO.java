package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Transaction;

import java.time.LocalDate;
import java.util.List;

public class AccountDTO {
    private long id;

    private String number;

    private LocalDate creationDate;
    private double balance;
    private List<Transaction> transactions;

    public AccountDTO() {
    }

    public AccountDTO(Account account) {
        this.number = account.getNumber();
        this.creationDate = account.getDate();
        this.balance = account.getBalance();
        this.transactions = account.getTransactions();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}