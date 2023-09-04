package com.mindhub.homebanking.services.implementation;

import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.ClientLoan;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.services.ClientLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientLoanImplementation implements ClientLoanService {
    @Autowired
    ClientLoanRepository clientLoanRepository;
    @Override
    public List<ClientLoan> findByClient(Client client){
        return clientLoanRepository.findByClient(client);
    }
    @Override
    public void save(ClientLoan clientLoan){
        clientLoanRepository.save(clientLoan);
    }
}
