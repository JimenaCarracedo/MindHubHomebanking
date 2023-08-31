package com.mindhub.homebanking.controllers;




import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @RequestMapping("/accounts")
    public List<AccountDTO> findAccount() {

        return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());

    }
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){

        return new AccountDTO(accountRepository.findById(id).orElse(null));
    }
    @GetMapping("clients/current/accounts")
    public List<AccountDTO> findAccount(Authentication authentication) {

        return accountRepository.findByClient(clientRepository.findByEmail(authentication.getName())).stream().map(AccountDTO::new).collect(toList());

    }
    @PostMapping(path = "/clients/current/accounts")
    public ResponseEntity<Object> createAccount(Authentication authentication, AccountDTO account) {
        Integer accountNumber = (int) Math.floor(Math.random()*Math.max(1, 100000));
        List <Account> accountsByClient = accountRepository.findByClient(clientRepository.findByEmail(authentication.getName()));
        if(accountsByClient.size()<3){
        accountRepository.save(new Account("VIN" + accountNumber.toString(), LocalDate.now(), 0, clientRepository.findByEmail(authentication.getName())));

            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }



}