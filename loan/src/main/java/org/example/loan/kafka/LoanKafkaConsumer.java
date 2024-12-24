package org.example.loan.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.loan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LoanKafkaConsumer {

    @Autowired
    private LoanService loanService;

    private static final Logger logger = LoggerFactory.getLogger(LoanKafkaConsumer.class);

    @KafkaListener(topics="account-events",groupId = "loan-group")
    public void consumeAccountDeleteEvent(String message) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode event = objectMapper.readTree(message);

            if("ACCOUNT_DELETED".equals(event.get("event").asText())){
                Long id = Long.valueOf(event.get("accountId").asText());
                logger.info("Account : " + id);
                loanService.deleteLoanByAccountId(id);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
