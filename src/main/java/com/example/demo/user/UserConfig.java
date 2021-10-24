package com.example.demo.user;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.transaction.Transaction;
import com.example.demo.transaction.TransactionRepository;
import com.example.demo.transaction.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner3(UserRepository userRepository, TransactionRepository transactionRepository, AccountRepository accountRepository, TransactionService transactionService) {
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

      User u = new User("T", "M", "t@gmail.com", "tm", "mi");

      Account account1 = new Account(543.75, "34523239");
      Account account2 = new Account(743.65, "43243123");
      Account account3 = new Account(400, "4324334");

      anbers.setAccount(account1);
      account1.setUser(anbers);

      averagesizedRod.setAccount(account2);
      account2.setUser(averagesizedRod);

      u.setAccount(account3);
      account3.setUser(u);

      userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, u));

    };
  }
}
