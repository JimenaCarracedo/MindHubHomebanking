package com.mindhub.homebanking.DTO;

import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Transaction;
import com.mindhub.homebanking.Models.TransactionType;

import java.time.LocalDate;

public class TransactionDTO {
    private long id;
    private TransactionType type;
    private double amount;
    private String description;
    private LocalDate date;

    private Account accounts;
    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.accounts = transaction.getAccount();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Account getAccount() {
        return accounts;
    }

    public void setAccount(Account account) {
        this.accounts = account;
    }
}
