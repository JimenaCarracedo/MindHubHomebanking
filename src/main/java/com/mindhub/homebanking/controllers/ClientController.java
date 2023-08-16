package com.mindhub.homebanking.controllers;



import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;
    @RequestMapping("/clients")
    public List<ClientDTO> findClient() {

        return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());

    }


    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){


        return new ClientDTO(clientRepository.findById(id).orElse(null));

    }

}
