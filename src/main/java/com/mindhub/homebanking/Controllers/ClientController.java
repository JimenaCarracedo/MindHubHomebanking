package com.mindhub.homebanking.Controllers;


import com.mindhub.homebanking.DTO.ClientDTO;
import com.mindhub.homebanking.DTO.ClientLoanDTO;
import com.mindhub.homebanking.Models.Client;
import com.mindhub.homebanking.Models.ClientLoan;
import com.mindhub.homebanking.Repositories.ClientLoanRepository;
import com.mindhub.homebanking.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;
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

