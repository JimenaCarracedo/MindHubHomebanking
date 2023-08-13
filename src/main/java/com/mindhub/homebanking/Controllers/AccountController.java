package com.mindhub.homebanking.Controllers;




import com.mindhub.homebanking.DTO.AccountDTO;
import com.mindhub.homebanking.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class AccountController {
    @Autowired
    AccountRepository accountRepository;

    @RequestMapping("/api/accounts")
    public List<AccountDTO> getAccounts(){
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
    }
    @RequestMapping("/api/accounts/{id}")
    public Optional<AccountDTO> getAccount(@PathVariable Long id){

        Optional<AccountDTO> account;
        account=accountRepository.findById(id).map(AccountDTO::new);
        return account;
    }
}