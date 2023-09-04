package com.mindhub.homebanking.services.implementation;

import com.mindhub.homebanking.models.Loan;
import com.mindhub.homebanking.repositories.LoanRepository;
import com.mindhub.homebanking.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class LoanImplementation implements LoanService {
    @Autowired
    LoanRepository loanRepository;
    @Override
    public Optional<Loan> findById(long Id){
        return loanRepository.findById(Id);
    }
    @Override
    public List<Loan> findAll(){
        return loanRepository.findAll();
    }
}
