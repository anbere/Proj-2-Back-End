package com.example.demo.user;

import java.util.Arrays;

import com.example.demo.Account.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner2(UserRepository repository) {
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
//      repository.saveAll(Arrays.asList(anbers, averagesizedRod));
    };
  }
}
