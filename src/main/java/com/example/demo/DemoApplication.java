package com.example.demo;

import com.example.demo.Account.Account;
import com.example.demo.Account.AccountRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private AccountRepository accountRepository;
  @Override
  public void run(String... args) throws Exception {
    User anbers = new User(
            "anbers",
            "password",
            "andres@gmail.com",
            "Andres",
            "Rodriguez"
    );

    User a = new User(
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

    a.setAccount(account2);
    account2.setUser(a);
    userRepository.save(anbers);
    userRepository.save(a);
  }
}
