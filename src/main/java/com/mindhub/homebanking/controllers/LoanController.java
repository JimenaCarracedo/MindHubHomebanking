package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ApplicationDTO;
import com.mindhub.homebanking.dtos.ClientLoanDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LoanController {
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoanRepository loanRepository;

    @RequestMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(loanName -> new LoanDTO(loanName)).collect(Collectors.toList());
        //return clientLoanRepository.findByClient(clientRepository.findByEmail(authentication.getName())).stream().map(clientLoan -> new ApplicationDTO(clientLoan)).collect(Collectors.toList());
    }

    @Transactional
    @RequestMapping(path = "api/loans", method = RequestMethod.POST)
    public ResponseEntity<Object> createLoan(@RequestBody ApplicationDTO applicationDTO) {
        if (applicationDTO.getLoanType().isEmpty() || applicationDTO.getAmount() == null || applicationDTO.getPayments() == null) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Loan newLoan = loanRepository.findByName(applicationDTO.getLoanType());
        if (newLoan == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!newLoan.getClientLoans().equals(applicationDTO.getClientLoans())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        loanRepository.save(newLoan);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}