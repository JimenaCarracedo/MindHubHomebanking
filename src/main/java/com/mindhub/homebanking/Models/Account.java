package com.mindhub.homebanking.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


import static com.mindhub.homebanking.Models.TransactionType.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;

    private LocalDate creationDate;
    private double balance;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    private Client client;


    @ManyToMany(mappedBy="account", fetch=FetchType.EAGER)
    private List<Transaction> transactions;
    public Account() {
    }

    public Account(String number, LocalDate creationDate, double balance, Client client) {

        this.number = number;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client =client;

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
    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    @JsonIgnore
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransaction(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(List<Transaction> transactions){
        for(int i=0; i<transactions.size(); i++){

            if (transactions.get(i).getType().equals(DEBIT)) {
                this.balance = this.balance - transactions.get(i).getAmount();

            } else if (transactions.get(i).getType().equals(CREDIT)) {
                this.balance = this.balance + transactions.get(i).getAmount();

            }
        }

    }


}