package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class TransactionController {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @RequestMapping("/transactions")
    public List<TransactionDTO> getTransaction(){
        return  transactionRepository.findAll().stream().map(transaction -> new TransactionDTO(transaction)).collect(Collectors.toList());
    }
    @Transactional
    @RequestMapping(path = "/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> createTransaction(Authentication authentication,
                                                    @RequestParam Double amount,
                                                    @RequestParam String description,
                                                    @RequestParam String fromAccountNumber,
                                                    @RequestParam String toAccountNumber){
        if(fromAccountNumber.isEmpty() || toAccountNumber.isEmpty() || amount==null || description.isEmpty()){

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(fromAccountNumber.equals(toAccountNumber)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Account accountTransaction = accountRepository.findByNumber(fromAccountNumber);
        if(accountTransaction==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(!accountTransaction.getClient().getEmail().equals(authentication.getName())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Account accountTo =accountRepository.findByNumber(toAccountNumber);
        if(accountTo==null){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if(accountTransaction.getBalance()<amount){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Transaction debitTransfer =new Transaction(TransactionType.DEBIT, -amount, description, LocalDate.now(), accountTransaction);
        Transaction creditTransfer =new Transaction(TransactionType.CREDIT, amount, description, LocalDate.now(), accountTo);
        accountTransaction.setBalance(accountTransaction.getBalance()-amount);
        accountTo.setBalance(accountTo.getBalance()+amount);
        transactionRepository.save(debitTransfer);
        transactionRepository.save(creditTransfer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
