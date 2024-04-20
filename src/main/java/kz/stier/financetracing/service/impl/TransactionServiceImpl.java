package kz.stier.financetracing.service.impl;

import kz.stier.financetracing.domain.Transaction;
import kz.stier.financetracing.repository.TransactionRepository;
import kz.stier.financetracing.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Override
    public void save(Transaction transaction) {
        repository.save(transaction);
    }
}
