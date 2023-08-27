package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.AccountDTO;
import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

import static com.mindhub.homebanking.models.TransactionType.CREDIT;
import static com.mindhub.homebanking.models.TransactionType.DEBIT;
import static java.util.stream.Collectors.toList;
@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ClientRepository clientRepository;

    @RequestMapping("/clients/current/cards")
    public List<CardDTO> findCard(Authentication authentication) {

        return cardRepository.findByClient(clientRepository.findByEmail(authentication.getName())).stream().map(CardDTO::new).collect(toList());

    }

    @RequestMapping(path = "/clients/current/cards", method = RequestMethod.POST)
    public ResponseEntity<Object> create(CardColor cardColor,
                                         CardType cardType,
                                         Authentication authentication) {
        String name =clientRepository.findByEmail(authentication.getName()).getFirstName()+" "+clientRepository.findByEmail(authentication.getName()).getLastName();
        Integer cardNumber1 = (int) Math.floor(Math.random()*Math.max(1, 10000));
        Integer cardNumber2 = (int) Math.floor(Math.random()*Math.max(1, 10000));
        Integer cardNumber3 = (int) Math.floor(Math.random()*Math.max(1, 10000));
        Integer cardNumber4 = (int) Math.floor(Math.random()*Math.max(1, 10000));
        List<Card> cardsByClient = cardRepository.findByClient(clientRepository.findByEmail(authentication.getName()));
        List<Card> cardsByType = cardRepository.findByType(cardType);
        if (cardsByClient.size() < 7 && cardsByType.size() < 4) {
                Card card = new Card(clientRepository.findByEmail(authentication.getName()), name, cardType, cardColor, cardNumber1.toString()+"-"+cardNumber2.toString()+"-"+cardNumber3.toString()+"-"+cardNumber4.toString(), 123, LocalDate.now().plusYears(5), LocalDate.now());
                cardRepository.save(card);
                return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
