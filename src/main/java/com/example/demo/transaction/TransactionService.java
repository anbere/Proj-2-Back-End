package com.example.demo.transaction;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions()
    {
        return transactionRepository.findAll();
    }

    public Transaction addNewTransaction(Transaction transaction)
    {
        Optional<Account> optional = accountRepository.findById(transaction.getOrigin());
        Optional<Account> optional2 = accountRepository.findById(transaction.getDestination());


        transactionRepository.save(transaction);
        return transaction;
    }

    public void deleteTransaction()
    {

    }
}
