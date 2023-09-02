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
    public List<LoanDTO> getLoans(){
        return loanRepository.findAll().stream().map(loanName -> new LoanDTO(loanName)).collect(Collectors.toList());
        //return clientLoanRepository.findByClient(clientRepository.findByEmail(authentication.getName())).stream().map(clientLoan -> new ApplicationDTO(clientLoan)).collect(Collectors.toList());
    }
    /*@Transactional
    @RequestMapping(path = "/loans", method = RequestMethod.POST)
    public ResponseEntity<Object> createLoan(Authentication authentication,
                                                    @RequestParam String loanType,
                                                    @RequestParam Double amount,
                                                    @RequestParam Integer payments,
                                                    @RequestParam String toAccountNumber) {
        if (loanType.isEmpty() || amount == null || payments==null) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Loan newLoan = loanRepository.findByLoanType(loanType);
        if (newLoan == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(!newLoan.getClientLoans().stream().map(clientLoans -> new ApplicationDTO(clientLoans).getId()).equals(clientRepository.findByEmail(authentication.getName()).getId())){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(!newLoan.getClientLoans().stream().map(clientLoans -> new ApplicationDTO(clientLoans).getLoanType()).equals(loanType)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }*/
}
