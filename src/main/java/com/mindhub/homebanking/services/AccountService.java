package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;

import javax.persistence.Id;
import java.util.List;

public interface AccountService {
    List<Account> findByClient(Client client);
    Account findById(long id);

    Account findByNumber(String number);
    List<Account> findAll();
    public void save(Account account);
}
