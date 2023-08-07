package com.mindhub.homebanking.Controllers;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.Models.Account;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Repositories.AccountRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController("localhost:8080/")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("api/clients")
    public List<ClientDTO> findClient() {

        return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());

    }


    @RequestMapping("api/clients/{id}")
    public Optional<Client> getClient(@PathVariable Long id){

        Optional<Client> clients;
        clients=clientRepository.findById(id);
        return clients;
    }

}

