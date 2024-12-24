package org.example.loan.repository;

import org.example.loan.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan>findByAccountId(Long accountId);

    void deleteLoanByAccountId(Long id);
}
