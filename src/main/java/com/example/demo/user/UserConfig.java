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
  CommandLineRunner commandLineRunner3(UserRepository userRepository, TransactionRepository transactionRepository, AccountRepository accountRepository) {
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
      Account account3 = new Account(400);

      anbers.setAccount(account1);
      account1.setUser(anbers);

      averagesizedRod.setAccount(account2);
      account2.setUser(averagesizedRod);

      u.setAccount(account3);
      account3.setUser(u);

      userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, u));

      Optional<User> user = userRepository.findByUsername("T");

      /*System.out.println("Before " + user.get().getAccount().getBalance());
      Transaction deposit = new Transaction(500);
      deposit.setOrigin(user.get().getAccount());
      deposit.setDestination(user.get().getAccount());
      TransactionService service = new TransactionService(transactionRepository, userRepository);
      service.deposit(deposit,"T");
      System.out.println("After: " + user.get().getAccount().getBalance());

      List<Transaction> t = service.getTransactions();
      System.out.println(t);*/

      System.out.println("Before " + anbers.getAccount().getBalance());
      System.out.println("Before (Me)" + u.getAccount().getBalance());

      Transaction pay = new Transaction("pay", 30, "I had a great time at dinner." );
      pay.setOrigin(anbers.getAccount());
      pay.setDestination(u.getAccount());
      TransactionService service = new TransactionService(transactionRepository, userRepository);
      service.addNewTransaction(pay, anbers.getUsername(), u.getUsername());

      System.out.println("After: " + anbers.getAccount().getBalance());
      System.out.println("After: (Me)" + u.getAccount().getBalance());

      List<Transaction> t = service.getTransactions();
      System.out.println(t);

      //Optional<User> op = userRepository.findById(3L);
      //System.out.println("hi");
      /*Account account1 = new Account(543.75, "34523239");
      Account account2 = new Account(743.65, "43243123");
      Account account3 = new Account(400);
      anbers.setAccount(account1);
      account1.setUser(anbers);

      averagesizedRod.setAccount(account2);
      account2.setUser(averagesizedRod);

      anbers.setAccount(account1);
      averagesizedRod.setAccount(account2);
      User u = new User("T", "M", "t@gmail.com", "t", "m");
      account3.setUser(u);
      u.setAccount(account3);
      userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, u));

      Transaction transaction = new Transaction("pay", 200, "This is working");
      Transaction deposit = new Transaction(500);

      TransactionService service = new TransactionService(transactionRepository, userRepository, accountRepository);
      service.deposit(deposit,"T");*/

      /*transactionRepository.save(deposit);
      TransactionService service = new TransactionService(transactionRepository);
      service.deposit(deposit, "anbers");*/

      /*System.out.println("accountId: " + userRepository.findByUsername("anders"));
      System.out.println("everything: " + anbers);
      System.out.println(anbers.getAccount());*/

      /*Transaction transaction = new Transaction("pending", 75.32, "This is working");
      transaction.setOrigin(account);
      transaction.setDestination(account2);
      //accountRepository.saveAll(Arrays.asList(account, account2));
      transactionRepository.save(transaction);
      Optional<Account> account3 = accountRepository.findById(1L);
      Optional<Account> account4 = accountRepository.findById(2L);
      System.out.println(account3.get().getOrigin() + "\n" + account4.get().getDestination());*/
      //System.out.println("account" + );

    };
  }
}
