package com.mindhub.homebanking.services;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Client findByEmail(String email);
    List<Client> findAll();
    Client findById(long id);

    public void save(Client client);
}
