package kz.stier.financetracing.service;

import kz.stier.financetracing.domain.Transaction;

public interface TransactionService {
    void save(Transaction transaction);
}
