package com.mindhub.homebanking.controllers;




import com.mindhub.homebanking.dtos.AccountDTO;

import com.mindhub.homebanking.models.Account;

import com.mindhub.homebanking.repositories.TransactionRepository;


import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientService;
import com.mindhub.homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private ClientService clientService;
    @Autowired
    TransactionService transactionService;
    @RequestMapping("/accounts")
    public List<AccountDTO> findAccount() {

        return accountService.findAll().stream().map(AccountDTO::new).collect(toList());

    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){

        return new AccountDTO(accountService.findById(id));
    }
    @GetMapping("clients/current/accounts")
    public List<AccountDTO> findAccount(Authentication authentication) {

        return accountService.findByClient(clientService.findByEmail(authentication.getName())).stream().map(AccountDTO::new).collect(toList());

    }
    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication, AccountDTO account) {
        Integer accountNumber = (int) Math.floor(Math.random()*Math.max(1, 100000));
        List <Account> accountsByClient = accountService.findByClient(clientService.findByEmail(authentication.getName()));
        if(accountsByClient.size()<3){
            accountService.save(new Account("VIN" + accountNumber.toString(), LocalDate.now(), 0, clientService.findByEmail(authentication.getName())));

            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }



}