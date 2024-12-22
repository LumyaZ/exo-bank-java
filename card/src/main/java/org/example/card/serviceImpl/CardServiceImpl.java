package org.example.card.serviceImpl;

import org.example.card.entity.Card;
import org.example.card.repository.CardRepository;
import org.example.card.rest.AccountServiceClient;
import org.example.card.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Card getCardById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
    }

    @Override
    public Card saveCard(Card card) {
        if (accountServiceClient.accountExists(card.getAccountId())) {
            return cardRepository.save(card);
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
    }

    @Override
    public void deleteCard(Long id) {
        cardRepository.deleteById(id);
    }
}
