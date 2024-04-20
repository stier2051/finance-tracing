package kz.stier.financetracing.repository;

import kz.stier.financetracing.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
