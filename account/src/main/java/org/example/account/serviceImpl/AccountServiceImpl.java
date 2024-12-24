package org.example.account.serviceImpl;

import org.example.account.dto.AccountDetailsDTO;
import org.example.account.entity.Account;
import org.example.account.kafka.AccountKafkaProducer;
import org.example.account.repository.AccountRepository;
import org.example.account.rest.ServiceClient;
import org.example.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private AccountKafkaProducer accountKafkaProducer;

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Long id) {
        accountKafkaProducer.sendAccountDeleteEvent(id);
        accountRepository.deleteById(id);
    }

    @Override
    public AccountDetailsDTO getAccountDetails(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        AccountDetailsDTO dto = new AccountDetailsDTO();
        dto.setId(account.getId());
        dto.setName(account.getName());
        dto.setEmail(account.getEmail());
        dto.setSolde(account.getSolde());
        dto.setCards(serviceClient.getCardsByAccountId(accountId));
        dto.setLoans(serviceClient.getLoansByAccountId(accountId));
        return dto;
    }
}
