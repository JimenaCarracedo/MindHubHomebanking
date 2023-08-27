package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByClient(Client client);
    List<Card> findByType(CardType cardType);
    List<Card> findByColor(CardColor cardColor);
}
