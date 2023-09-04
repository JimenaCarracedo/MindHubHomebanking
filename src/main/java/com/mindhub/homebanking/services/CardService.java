package com.mindhub.homebanking.services;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.Client;

import java.util.List;

public interface CardService {
    List<Card> findByClient(Client client);
    void save(Card card);
}
