package com.example.demo.user;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.friends.FriendService;
import com.example.demo.transaction.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfig {

	@Bean
	CommandLineRunner commandLineRunner2(UserRepository userRepository, AccountRepository accountRepository, TransactionRepository transactionRepository, FriendService friendService) {
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

//       Account account1 = new Account();
//
//       anbers.setAccount(account1);
//       account1.setUser(anbers);
//       userRepository.save(anbers);

    };
  }
}
