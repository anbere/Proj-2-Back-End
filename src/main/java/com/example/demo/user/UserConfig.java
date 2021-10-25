package com.example.demo.user;

import com.example.demo.account.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class UserConfig {

  @Bean
  CommandLineRunner commandLineRunner2(UserRepository userRepository, UserService userService) {
    return args -> {
      User anbers = new User(
          "anbers",
          "password",
          "andres@gmasdfail.com",
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

      // userRepository.saveAllAndFlush(Arrays.asList(anbers, averagesizedRod, u));

      //Optional<User> op = userRepository.findById(3L);
      //System.out.println("hi");
       Account account1 = new Account(543.75, "34523239");
       Account account2 = new Account(743.65, "43243123");
       Account account3 = new Account(400, "4234");

       anbers.setAccount(account1);
       account1.setUser(anbers);

       averagesizedRod.setAccount(account2);
       account2.setUser(averagesizedRod);

       u.setAccount(account3);
       account3.setUser(u);

       userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, u));

        // Optional<User> user = userRepository.findByUsername(anbers.getUsername());
        // System.out.println("From UserConfig: " + user.get().getAccount());
    };
  }
}
