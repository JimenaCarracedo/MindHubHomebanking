package com.mindhub.homebanking.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static com.mindhub.homebanking.Models.TransactionType.DEPOSIT;
import static com.mindhub.homebanking.Models.TransactionType.EXTRACTION;


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

    @OneToMany(mappedBy="account", fetch=FetchType.EAGER)
    Set<Transaction> transaction=new HashSet<>();
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
    public Set<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(Set<Transaction> transaction) {
        this.transaction = transaction;
    }

    public void addTransaction(Transaction transaction){


        if(transaction.getType().equals(EXTRACTION)){
            this.balance = this.balance-transaction.getAmount();
        } else if (transaction.getType().equals(DEPOSIT)) {
            this.balance = this.balance+transaction.getAmount();

        }
    }


    }

