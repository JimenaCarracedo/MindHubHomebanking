package com.mindhub.homebanking.services.implementation;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientImplementation implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    @Override
    public List<Client> findAll(){
        return clientRepository.findAll();
    }
    @Override
    public Client findById(long id){
       return clientRepository.findById(id).orElse(null);
    }
    @Override
    public void save(Client client){
        clientRepository.save(client);
    }
}
