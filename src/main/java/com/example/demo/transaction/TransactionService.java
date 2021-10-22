package com.example.demo.transaction;

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
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getTransactions()
    {
        return transactionRepository.findAll();
    }

    public Transaction payTransaction(Transaction transaction, String originUsername, String destinationUsername)
    {
        Optional<User> optionalOrigin = userRepository.findByUsername(originUsername);
        System.out.println("origin: " + optionalOrigin.get());
        Optional<User> optionalDestination = userRepository.findByUsername(destinationUsername);
        System.out.println("desti: " + optionalDestination.get());
        /*Optional<Account> optional = accountRepository.findById(transaction.getOrigin().getId());
        Optional<Account> optional2 = accountRepository.findById(transaction.getDestination().getId());*/

        if(optionalOrigin.isPresent() && optionalDestination.isPresent() && transaction.getAmount() > 0)
        {

            System.out.println("Inside setting account" + optionalOrigin.get().getAccount());
            System.out.println("Inside setting account" + optionalDestination.get().getAccount());
            transaction.setOrigin(optionalOrigin.get().getAccount());
            transaction.setDestination(optionalDestination.get().getAccount());
            System.out.println("Inside setting account to transaction origin: " + transaction.getOrigin());
            System.out.println("Inside setting account: " +  transaction.getDestination());

            if(optionalOrigin.get().getAccount().getBalance() >= transaction.getAmount())
            {
                double accountBalance = transaction.getOrigin().getBalance();
                System.out.println("origin Balance: " +  accountBalance);

                double accountBalance2 = transaction.getDestination().getBalance();
                System.out.println("destination Balance: " +  accountBalance2);

                double amount = transaction.getAmount();
                System.out.println("amount of the transaction: " + amount);

                transaction.getOrigin().setBalance(accountBalance - amount);
                System.out.println("Operation to subtract from origin balance " + transaction.getOrigin().getBalance());

                transaction.getDestination().setBalance(accountBalance2 + amount);
                System.out.println("Operation to add to destination balance " + transaction.getDestination().getBalance());

                transaction.setStatus("success");
                accountRepository.saveAll(Arrays.asList(transaction.getOrigin(), transaction.getDestination()));
                transactionRepository.save(transaction);
                return transaction;


                /*optionalOrigin.get().getAccount().setBalance(accountBalance - amount);
                optionalDestination.get().getAccount().setBalance(accountBalance2 + amount);*/
            }
            else{
                System.out.println("Invalid transaction amount");
                transaction.setStatus("failed");
                transactionRepository.save(transaction);
                return transaction;
            }
        }
        else
        {
            transaction.setStatus("failed");
            transactionRepository.save(transaction);
            return transaction;
        }

    }

    public Transaction deposit(Transaction transaction, String username)
    {
        Optional<User> depositor = userRepository.findByUsername(username);

        if(depositor.isPresent() && transaction.getAmount() > 0)
        {
            transaction.setOrigin(depositor.get().getAccount());
            double balance = depositor.get().getAccount().getBalance();
            double amount = transaction.getAmount();
            transaction.getOrigin().setBalance(balance + amount);

            accountRepository.save(transaction.getOrigin());
            transaction.setStatus("success");
            transactionRepository.save(transaction);
            return transaction;
        }

            //throw an user exception or invalid amount
            transaction.setStatus("failed");
            transactionRepository.save(transaction);
            return transaction;
    }

    public void deleteTransaction()
    {

    }
}
