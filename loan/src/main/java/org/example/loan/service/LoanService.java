package org.example.loan.service;

import org.example.loan.entity.Loan;

import java.util.List;

public interface LoanService {

    public List<Loan> getAllLoans();

    public Loan getLoanById(Long id);

    public Loan saveLoan(Loan loan);

    public void deleteLoan(Long id);

    public List<Loan> getLoansByAccountId(Long accountId);

}
