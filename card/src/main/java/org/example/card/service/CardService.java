package org.example.card.service;

import org.example.card.entity.Card;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CardService {
    public List<Card> getAllCards();

    public Card getCardById(Long id);

    public Card saveCard(Card card);

    public void deleteCard(Long id);

    List<Card> getCardsByAccountId(Long accountId);

    public void deleteCardByAccountId(Long id);
}
