package com.example.demo.transaction;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private AccountRepository accountRepository;
    private UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AccountRepository accountRepository)
    {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions()
    {
        return transactionRepository.findAll();
    }

    public Transaction addNewTransaction(Transaction transaction, String originUsername, String destinationUsername)
    {
        Optional<User> optionalOrigin = userRepository.findByUsername(originUsername);
        Optional<User> optionalDestination = userRepository.findByUsername(destinationUsername);

        /*Optional<Account> optional = accountRepository.findById(transaction.getOrigin().getId());
        Optional<Account> optional2 = accountRepository.findById(transaction.getDestination().getId());*/

        if(optionalOrigin.isPresent() && optionalDestination.isPresent() && transaction.getAmount() > 0)
        {
            if(optionalOrigin.get().getAccount().getBalance() >= transaction.getAmount())
            {
                double accountBalance = optionalOrigin.get().getAccount().getBalance();
                double accountBalance2 = optionalDestination.get().getAccount().getBalance();
                double amount = transaction.getAmount();

                optionalOrigin.get().getAccount().setBalance(accountBalance - amount);
                optionalDestination.get().getAccount().setBalance(accountBalance2 + amount);
            }
        }
        else
        {
            transaction.setStatus("failed");
            transactionRepository.save(transaction);
            return transaction;
        }

        transaction.setStatus("success");
        transactionRepository.save(transaction);
        return transaction;

    }

    public Transaction deposit(Transaction transaction, String username)
    {
        Optional<User> depositor = userRepository.findByUsername(username);
        System.out.println("Got Here");
        if(depositor.isPresent() && transaction.getAmount() > 0)
        {
            System.out.println("Got Here2");
            double balance = depositor.get().getAccount().getBalance();
            double amount = transaction.getAmount();
            depositor.get().getAccount().setBalance(balance + amount);
        }
        else
        {
            transaction.setStatus("failed");
            transactionRepository.save(transaction);
            return transaction;
        }

        System.out.println("Got Here3");
        transaction.setStatus("success");
        transactionRepository.save(transaction);
        return transaction;

    }

    public void deleteTransaction()
    {

    }
}
