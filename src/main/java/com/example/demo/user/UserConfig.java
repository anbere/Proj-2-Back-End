package com.example.demo.user;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner2(UserRepository repository, AccountRepository accountRepository) {
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

//      Account account1 = new Account(543.75, "34523239");
//      Account account2 = new Account(743.65, "43243123");
//      anbers.setAccount(account1);
//      account1.setUser(anbers);
//
//      averagesizedRod.setAccount(account2);
//      account2.setUser(averagesizedRod);
//      Account account = new Account();
//      Account account2 = new Account();
//      anbers.setAccount(account);
//      averagesizedRod.setAccount(account2);
//      repository.saveAll(Arrays.asList(anbers, averagesizedRod));
    };
  }
}
