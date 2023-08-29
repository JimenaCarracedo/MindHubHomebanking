package com.mindhub.homebanking.repositories;

import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.models.CardColor;
import com.mindhub.homebanking.models.CardType;
import com.mindhub.homebanking.models.Client;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import javax.persistence.Id;
import java.util.List;

import static com.mindhub.homebanking.models.CardType.CREDIT;
import static com.mindhub.homebanking.models.CardType.DEBIT;


public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByClient(Client client);
    List<Card> findByType(CardType cardType);


}