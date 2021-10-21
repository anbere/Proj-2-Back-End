package com.example.demo;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.transaction.Transaction;
import com.example.demo.transaction.TransactionRepository;
import com.example.demo.transaction.TransactionService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;


@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
//  @Autowired
//  private UserRepository userRepository;
//  @Autowired
//  private AccountRepository accountRepository;
//  @Autowired
//  private TransactionRepository transactionRepository;
//
//
//  public void run(String... args) throws Exception {
//    User anbers = new User(
//            "anbers",
//            "password",
//            "andres@gmail.com",
//            "Andres",
//            "Rodriguez"
//    );
//
//    User a = new User(
//            "averagesizedRod",
//            "password",
//            "rodrigo@gmail.com",
//            "Rodrigo",
//            "Flores"
//    );
//
//    Account account1 = new Account(543.75, "34523239");
//    Account account2 = new Account(743.65, "43243123");
//    anbers.setAccount(account1);
//    account1.setUser(anbers);
//
//    a.setAccount(account2);
//    account2.setUser(a);
//    userRepository.save(anbers);
//    userRepository.save(a);
//
//    accountRepository.save(account1);
//    accountRepository.save(account2);
//
//    Transaction transaction = new Transaction("pay", 200, "testing if this sends", LocalDate.now(), "posted", account1.getId(), account2.getId());
//    TransactionService transactionService = new TransactionService(transactionRepository);
//    transactionService.addNewTransaction(transaction);
//    transactionRepository.save(transaction);

  // }
}
