package com.mindhub.homebanking.controllers;
import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.TransactionService;
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
public class TransactionController {


    @Autowired
    private AccountRepository accountService;

    @Autowired
    private ClientService clientService;
    @Autowired
    private TransactionService transactionService;
    @GetMapping("/transactions")
    public List<TransactionDTO> getTransaction(){
        return  transactionService.findAll();
    }
    @Transactional
    @PostMapping(path = "/transactions")
    public ResponseEntity<Object> createTransaction(Authentication authentication,
                                                    @RequestParam Double amount,
                                                    @RequestParam String description,
                                                    @RequestParam String fromAccountNumber,
                                                    @RequestParam String toAccountNumber) {
        if (fromAccountNumber.isEmpty() || toAccountNumber.isEmpty() || amount == null || description.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (fromAccountNumber.equals(toAccountNumber)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Account accountTransaction = accountService.findByNumber(fromAccountNumber);
        if (accountTransaction == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if (!accountTransaction.getClient().getEmail().equals(authentication.getName())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Account accountTo = accountService.findByNumber(toAccountNumber);
        if (accountTo == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (accountTransaction.getBalance() < amount) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

            Transaction debitTransfer = new Transaction(TransactionType.DEBIT, -amount, description, LocalDate.now(), accountTransaction);
            Transaction creditTransfer = new Transaction(TransactionType.CREDIT, amount, description, LocalDate.now(), accountTo);
            accountTransaction.setBalance(accountTransaction.getBalance() - amount);
            accountTo.setBalance(accountTo.getBalance() + amount);
            transactionService.save(debitTransfer);
            transactionService.save(creditTransfer);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

}
