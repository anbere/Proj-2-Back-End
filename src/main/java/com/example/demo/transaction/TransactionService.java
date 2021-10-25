package com.example.demo.transaction;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    //going to go badly
    public List<Transaction> getUserTransactions(String username)
    {
        Optional<User> optionalOrigin = userRepository.findByUsername(username);
        Account originAccount = optionalOrigin.get().getAccount();
        List<Transaction> transactionList = transactionRepository.findByOrigin(originAccount);
        return transactionList;
    }

    public List<Transaction> getRequests(String type, String username)
    {
        Optional<User> requester = userRepository.findByUsername(username);
        Account requesterAccount = requester.get().getAccount();
        return transactionRepository.findByTypeAndDestination(type, requesterAccount);
    }

    public Transaction payTransaction(Transaction transaction, String originUsername, String destinationUsername)
    {
        Optional<User> optionalOrigin = userRepository.findByUsername(originUsername);
        //System.out.println("origin: " + optionalOrigin.get());
        Optional<User> optionalDestination = userRepository.findByUsername(destinationUsername);
        //System.out.println("desti: " + optionalDestination.get());

        /*Optional<Account> optional = accountRepository.findById(transaction.getOrigin().getId());
        Optional<Account> optional2 = accountRepository.findById(transaction.getDestination().getId());*/

        if(optionalOrigin.isPresent() && optionalDestination.isPresent() && transaction.getAmount() > 0)
        {
            /*System.out.println("Inside setting account" + optionalOrigin.get().getAccount());
            System.out.println("Inside setting account" + optionalDestination.get().getAccount());*/

            transaction.setOrigin(optionalOrigin.get().getAccount());
            transaction.setDestination(optionalDestination.get().getAccount());
            transaction.setTime(LocalDateTime.now());

            /*System.out.println("Inside setting account to transaction origin: " + transaction.getOrigin());
            System.out.println("Inside setting account: " +  transaction.getDestination());*/

            if(optionalOrigin.get().getAccount().getBalance() >= transaction.getAmount())
            {
                double accountBalance = transaction.getOrigin().getBalance();
                //System.out.println("origin Balance: " +  accountBalance);

                double accountBalance2 = transaction.getDestination().getBalance();
                //System.out.println("destination Balance: " +  accountBalance2);

                double amount = transaction.getAmount();
                //System.out.println("amount of the transaction: " + amount);

                transaction.getOrigin().setBalance(accountBalance - amount);
                //System.out.println("Operation to subtract from origin balance " + transaction.getOrigin().getBalance());

                transaction.getDestination().setBalance(accountBalance2 + amount);
                //System.out.println("Operation to add to destination balance " + transaction.getDestination().getBalance());

                transaction.setStatus("success");
                accountRepository.saveAll(Arrays.asList(transaction.getOrigin(), transaction.getDestination()));
                transactionRepository.save(transaction);
                return transaction;

                /*optionalOrigin.get().getAccount().setBalance(accountBalance - amount);
                optionalDestination.get().getAccount().setBalance(accountBalance2 + amount);*/
            }
        }

            transaction.setStatus("failed");
            transactionRepository.save(transaction);

        /*if(!optionalOrigin.isPresent())
            throw new NoSuchElementException("Could not find your account");
        if(!optionalDestination.isPresent())
            throw new NoSuchElementException("Could not find friend's account");
        if(transaction.getAmount() < 0 || transaction.getAmount() < optionalOrigin.get().getAccount().getBalance())
            throw new IllegalStateException("Value is not valid");*/

            return transaction;
    }

    public Transaction deposit(Transaction transaction, String username)
    {
        Optional<User> depositor = userRepository.findByUsername(username);
        transaction.setType("Deposit");

        if(depositor.isPresent() && transaction.getAmount() > 0)
        {
            transaction.setOrigin(depositor.get().getAccount());
            double balance = depositor.get().getAccount().getBalance();
            double amount = transaction.getAmount();
            transaction.getOrigin().setBalance(balance + amount);
            transaction.setTime(LocalDateTime.now());
            accountRepository.save(transaction.getOrigin());
            transaction.setStatus("success");
            return transactionRepository.save(transaction);
            //System.out.println(transactionRepository.save(transaction));
        }

        transaction.setStatus("failed");
        transactionRepository.save(transaction);

        /*if(!depositor.isPresent())
            throw new NoSuchElementException("This account does not exist");
        if(transaction.getAmount() < 0)
            throw new IllegalStateException("Value is not valid");*/

         return transaction;
    }

    public Transaction requestTransaction(Transaction transaction, String requester, String payer)
    {
        Optional<User> optionalRequester = userRepository.findByUsername(requester);
        Optional<User> optionalPayer = userRepository.findByUsername(payer);

        if(optionalRequester.isPresent() && optionalPayer.isPresent() && transaction.getAmount() > 0)
        {
            transaction.setOrigin(optionalRequester.get().getAccount());
            transaction.setDestination(optionalPayer.get().getAccount());
            transaction.setStatus("pending");
            transaction.setTime(LocalDateTime.now());
            transactionRepository.save(transaction);
            return transaction;
        }

        transaction.setStatus("failed");
        transactionRepository.save(transaction);
        return transaction;
    }

    public Transaction acceptRequestTransaction(Transaction transaction, String choice)
    {
        Optional<Transaction> temp = transactionRepository.findById(transaction.getId());
        temp.get().setTime(LocalDateTime.now());
        System.out.println("TransactionId " + temp.get());
        if(temp.get().getType().equals("request") && temp.get().getStatus().equals("pending") && choice.equals("accept") && temp.get().getDestination().getBalance() >= temp.get().getAmount())
        {
            payAccept(temp.get());
            return transactionRepository.save(temp.get());
        }

        if(transaction.getType().equals("request") && transaction.getStatus().equals("pending") && choice.equals("reject"))
        {
            temp.get().setStatus("rejected");
            return transactionRepository.save(temp.get());
        }

        temp.get().setStatus("failed");

        /*if(transaction.getAmount() < 0 || temp.get().getAmount() < temp.get().getDestination().getBalance())
            throw new IllegalStateException("Value is not valid");*/

        return transactionRepository.save(temp.get());
    }

    public void payAccept(Transaction transaction)
    {
        double requesterBalance = transaction.getOrigin().getBalance();
        double payerBalance = transaction.getDestination().getBalance();
        double amount = transaction.getAmount();

        transaction.getDestination().setBalance(payerBalance - amount);
        transaction.getOrigin().setBalance(requesterBalance + amount);
        transaction.setStatus("success");
    }

    public void deleteTransaction()
    {

    }

//	public List<Transaction> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
