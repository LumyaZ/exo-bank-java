package org.example.account.rest;

import org.example.account.dto.CardDTO;
import org.example.account.dto.LoanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class ServiceClient {
    private final RestTemplate restTemplate;
    private static final String CARD_SERVICE_URL = "http://card/cards/accounts/";
    private static final String LOAN_SERVICE_URL = "http://loan/loans/accounts/";

    @Autowired
    public ServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // This method retrieves a list of CardDTO objects associated with the given account ID
    // It sends an HTTP GET request to the Card Service using its service name (load balanced),
    // and returns the list of cards. If there is an error (e.g., service unavailable), it returns an empty list.
    public List<CardDTO> getCardsByAccountId(Long accountId) {
        try {
            ResponseEntity<List<CardDTO>> response = restTemplate.exchange(
                    CARD_SERVICE_URL + accountId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CardDTO>>() {});

            return response.getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    // This method retrieves a list of LoanDTO objects associated with the given account ID
    // It sends an HTTP GET request to the Loan Service using its service name (load balanced),
    // and returns the list of loans. If there is an error (e.g., service unavailable), it returns an empty list.
    public List<LoanDTO> getLoansByAccountId(Long accountId) {
        try {
            ResponseEntity<List<LoanDTO>> response = restTemplate.exchange(
                    LOAN_SERVICE_URL + accountId,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<LoanDTO>>() {});

            return response.getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

}
