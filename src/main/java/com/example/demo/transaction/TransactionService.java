package com.example.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

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
        transactionRepository.save(transaction);
        return transaction;
    }

    public void deleteTransaction()
    {

    }

//	public List<Transaction> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
