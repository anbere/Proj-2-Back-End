package com.example.demo.transaction;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, AccountRepository accountRepository)
    {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getTransactions()
    {
        return transactionRepository.findAll();
    }

//    public List<Transaction> getTransactionsForUser(Long id) {
//
//    }
//
//    public Transaction addNewTransaction(Transaction transaction, String originUsername, String destinationUsername)
//    {
//        Optional<User> optionalOrigin = userRepository.findByUsername(originUsername);
//        Optional<User> optionalDestination = userRepository.findByUsername(destinationUsername);
//
//        /*Optional<Account> optional = accountRepository.findById(transaction.getOrigin().getId());
//        Optional<Account> optional2 = accountRepository.findById(transaction.getDestination().getId());*/
//
//        if(optionalOrigin.isPresent() && optionalDestination.isPresent() && transaction.getAmount() > 0)
//        {
//            if(optionalOrigin.get().getAccount().getBalance() >= transaction.getAmount())
//            {
//                double accountBalance = optionalOrigin.get().getAccount().getBalance();
//                double accountBalance2 = optionalDestination.get().getAccount().getBalance();
//                double amount = transaction.getAmount();
//
//                optionalOrigin.get().getAccount().setBalance(accountBalance - amount);
//                optionalDestination.get().getAccount().setBalance(accountBalance2 + amount);
//            }
//        }
//        else
//        {
//            transaction.setStatus("failed");
//            transactionRepository.save(transaction);
//            return transaction;
//        }
//
//        transaction.setStatus("success");
//        transactionRepository.save(transaction);
//        return transaction;
//
//    }

    public Account deposit(Transaction transaction, String username)
    {
        Optional<User> depositor = userRepository.findByUsername(username);
        transaction.setType("Deposit");

        if(depositor.isPresent() && transaction.getAmount() > 0)
        {
            transaction.setOrigin(depositor.get().getAccount());
            double balance = depositor.get().getAccount().getBalance();
            double amount = transaction.getAmount();
            transaction.getOrigin().setBalance(balance + amount);

            Account updatedBalance = accountRepository.save(transaction.getOrigin());
            transaction.setStatus("Success");
            transaction.setDate(LocalDate.now());
            transaction.setComment("Deposit of: " + amount);
            transactionRepository.save(transaction);
            return updatedBalance;
            //System.out.println(transactionRepository.save(transaction));
        }else
        {
            transaction.setStatus("Failed");

            if(!depositor.isPresent()){
                transaction.setComment("This account does not exist");
                transactionRepository.save(transaction);
                throw new IllegalStateException("This account does not exist");
            }else{ // Deposit amount is < 0
                transaction.setComment("Must be a deposit amount greater than 0");
                transactionRepository.save(transaction);
                throw new IllegalStateException("Value is not valid");
            }

        }

    }
}
