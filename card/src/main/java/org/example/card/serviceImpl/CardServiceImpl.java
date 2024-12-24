package org.example.card.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.card.entity.Card;
import org.example.card.repository.CardRepository;
import org.example.card.rest.AccountServiceClient;
import org.example.card.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    private static final Logger logger = LoggerFactory.getLogger(CardServiceImpl.class);


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

    @Override
    public List<Card> getCardsByAccountId(Long accountId) {
        return cardRepository.findByAccountId(accountId);
    }

    @Override
    @Transactional
    public void deleteCardByAccountId(Long id) {
        logger.info("Deleting card in service impl : " + id);
        cardRepository.deleteCardByAccountId(id);
    }
}
