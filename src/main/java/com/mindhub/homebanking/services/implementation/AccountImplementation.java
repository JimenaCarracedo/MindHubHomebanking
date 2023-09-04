package com.mindhub.homebanking.services.implementation;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
@Service
public class AccountImplementation implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public List<Account> findByClient(Client client){
        return accountRepository.findByClient(client);
    }
    @Override
    public Account findById(long id){
        return accountRepository.findById(id);
    }
    @Override
    public Account findByNumber(String number){
        return accountRepository.findByNumber(number);
    }
    @Override
    public List<Account> findAll(){
        return accountRepository.findAll();
    }
    @Override
    public void save(Account account){
        accountRepository.save(account);
    }
}
