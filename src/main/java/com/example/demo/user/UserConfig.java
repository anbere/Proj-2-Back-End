package com.example.demo.user;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.transaction.Transaction;
import com.example.demo.transaction.TransactionRepository;
import com.example.demo.transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner2(UserRepository userRepository, AccountRepository accountRepository, TransactionRepository transactionRepository) {


    return args -> {
      User anbers = new User(
          "anbers",
          "password",
          "andres@gmail.com",
          "Andres",
          "Rodriguez"
      );

      User averagesizedRod = new User(
          "averagesizedRod",
          "password",
          "rodrigo@gmail.com",
          "Rodrigo",
          "Flores"
      );



      Account account1 = new Account(543.75, "34523239");
      Account account2 = new Account(743.65, "43243123");
      anbers.setAccount(account1);
      account1.setUser(anbers);

      averagesizedRod.setAccount(account2);
      account2.setUser(averagesizedRod);
      userRepository.save(anbers);
      userRepository.save(averagesizedRod);

      accountRepository.save(account1);
      accountRepository.save(account2);

      Transaction transaction = new Transaction("pay", 200, "testing if this sends", LocalDate.now(), "accepted", account2.getId());
     // Transaction transaction1 = new Transaction("pay", 300, "testing if this sends", LocalDate.now(), "accepted");

      account1.setTransactions();
      TransactionService transactionService = new TransactionService(transactionRepository);
      transactionService.addNewTransaction(transaction);
     // transactionService.addNewTransaction(transaction1);
      //transactionRepository.save(transaction);
      //transactionRepository.save(transaction1);

//      Account account1 = new Account(543.75, "34523239");
//      Account account2 = new Account(743.65, "43243123");
//      anbers.setAccount(account1);
//      account1.setUser(anbers);
//
//      averagesizedRod.setAccount(account2);
//      account2.setUser(averagesizedRod);
      /*Account account = new Account();
      Account account2 = new Account();
      anbers.setAccount(account);
      averagesizedRod.setAccount(account2);
      repository.saveAll(Arrays.asList(anbers, averagesizedRod));*/
    };
  }
}
