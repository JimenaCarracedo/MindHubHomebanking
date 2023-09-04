package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;

import java.util.List;

public interface ClientLoanService {
    List<ClientLoan> findByClient(Client client);
    void save(ClientLoan clientLoan);
}
