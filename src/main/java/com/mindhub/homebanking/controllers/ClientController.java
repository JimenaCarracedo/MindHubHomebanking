package com.mindhub.homebanking.controllers;




import com.mindhub.homebanking.dtos.ClientDTO;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientLoanRepository;
import com.mindhub.homebanking.repositories.ClientRepository;


import com.mindhub.homebanking.services.AccountService;
import com.mindhub.homebanking.services.ClientLoanService;
import com.mindhub.homebanking.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientLoanService clientLoanService;
    @Autowired
    private AccountService accountService;

    @RequestMapping("/admin")
    public List<ClientDTO> findClient() {

        return clientService.findAll().stream().map(ClientDTO::new).collect(toList());

    }
    @RequestMapping("/admin/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id){


        return new ClientDTO(clientService.findById(id));

    }
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(

            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password) {



        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

            return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);

        }



        if (clientService.findByEmail(email) !=  null) {

            return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);

        }

        Integer accountNumber = (int) Math.floor(Math.random()*Math.max(1, 100000));
        Client client = new Client(firstName, lastName, email, passwordEncoder.encode(password));
        clientService.save(client);
        Account account = new Account("VIN"+accountNumber, LocalDate.now(), 0, client);
        accountService.save(account);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    @RequestMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {

        return new ClientDTO(clientService.findByEmail(authentication.getName()));

    }

}