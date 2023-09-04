package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Loan;

import java.util.List;
import java.util.Optional;

public interface LoanService {
    Optional<Loan> findById(long Id);
    List<Loan> findAll();
}
