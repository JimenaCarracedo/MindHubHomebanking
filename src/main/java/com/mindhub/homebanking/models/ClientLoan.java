package com.mindhub.homebanking.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ClientLoan{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;
    private double amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="clientLoan_id")
    private Client client;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="loan_id")
    private Loan loan;

    private int payments;
    public ClientLoan() {
    }

    public ClientLoan(Loan loan, Client clientLoan, double amount, int payments) {
        this.loan=loan;
        this.client = clientLoan;
        this.amount = amount;
        this.payments=payments;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPayments() {
        return payments;
    }

    public void setPayments(int payments) {
        this.payments = payments;
    }


    public Client getClientLoan() {
        return client;
    }

    public void setClient(Client clientLoan) {
        this.client = clientLoan;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }


}
