package com.mindhub.homebanking.services.implementation;


import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardImplementation implements CardService {
    @Autowired
    CardRepository cardRepository;
    @Override
    public List<Card> findByClient(Client client){
        return cardRepository.findByClient(client);
    }
    @Override
    public void save(Card card){
        cardRepository.save(card);
    }
}
