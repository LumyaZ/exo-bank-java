package org.example.loan.serviceImpl;

import jakarta.transaction.Transactional;
import org.example.loan.entity.Loan;
import org.example.loan.repository.LoanRepository;
import org.example.loan.rest.AccountServiceClient;
import org.example.loan.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public Loan getLoanById(Long id) {
        return loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
    }

    @Override
    public Loan saveLoan(Loan loan) {
        if (accountServiceClient.accountExists(loan.getAccountId())) {
            return loanRepository.save(loan);
        } else {
            throw new IllegalArgumentException("Account does not exist");
        }
    }

    @Override
    public void deleteLoan(Long id) {
        loanRepository.deleteById(id);
    }

    @Override
    public List<Loan> getLoansByAccountId(Long accountId) {
        return loanRepository.findByAccountId(accountId);
    }

    @Override
    @Transactional
    public void deleteLoanByAccountId(Long id) {
        logger.info("Deleting loan in service impl : " + id);
        loanRepository.deleteLoanByAccountId(id);
    }
}
