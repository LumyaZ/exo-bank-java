package org.example.card.repository;

import org.example.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card>findByAccountId(Long accountId);

    void deleteCardByAccountId(Long id);
}
