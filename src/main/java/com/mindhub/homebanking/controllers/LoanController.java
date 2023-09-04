package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.LoanApplicationDTO;
import com.mindhub.homebanking.dtos.LoanDTO;
import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/loans")
    public List<LoanDTO> getLoans() {
        return loanRepository.findAll().stream().map(loanName -> new LoanDTO(loanName)).collect(Collectors.toList());
        //return clientLoanRepository.findByClient(clientRepository.findByEmail(authentication.getName())).stream().map(clientLoan -> new ApplicationDTO(clientLoan)).collect(Collectors.toList());
    }


    @RequestMapping(path = "/loans", method = RequestMethod.POST)
    public ResponseEntity<Object> createLoan(@RequestBody LoanApplicationDTO loanApplicationDTO, Authentication authentication) {

        if (loanApplicationDTO == null) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Loan newLoan = loanRepository.findById(loanApplicationDTO.getLoanId()).orElse(null);
        if (newLoan == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Account account = accountRepository.findByNumber(loanApplicationDTO.getToAccountNumber());
        if (!account.getClient().equals(clientRepository.findByEmail(authentication.getName()))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


        if (account == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Transaction transaction = new Transaction(TransactionType.CREDIT, loanApplicationDTO.getAmount(), loanApplicationDTO.getToAccountNumber() + " loan approved", LocalDate.now(), account);
        if (transaction.getAmount() > newLoan.getMaxAmount()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!newLoan.getPayments().contains(loanApplicationDTO.getPayments())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        ClientLoan clientLoan = new ClientLoan(loanApplicationDTO.getAmount() * 1.2, loanApplicationDTO.getPayments());
        clientLoan.setLoan(newLoan);
        clientLoan.getLoan().setName(newLoan.getName());
        clientLoan.setClient(clientRepository.findByEmail(authentication.getName()));
        clientLoanRepository.save(clientLoan);
        accountRepository.save(account);
        transactionRepository.save(transaction);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}