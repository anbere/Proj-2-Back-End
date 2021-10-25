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
						
			User terrell = new User(
					"T", "M", "t@gmail.com", "tm", "mi");

			Account account1 = new Account(543.75, "34523239");
			Account account2 = new Account(743.65, "43243123");
			Account account3 = new Account(400);

			//user anbers is set to account 1, and account 1 is set to user anbers
			// anbers.setAccount(account1);
			// account1.setUser(anbers);

			// averagesizedRod.setAccount(account2);
			// account2.setUser(averagesizedRod);

			// //user terrell is set to account 3, and account 3 is set to user terrell
			// terrell.setAccount(account3);
			// account3.setUser(terrell);
			
// 			userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, terrell));
// //			User u = userRepository.getById(1L);
			
			
// 			 //add User references Friend
// 	         friendService.saveFriend(anbers, 2);
	         
// 	         friendService.saveFriend(terrell, 1);
	         
// 	         friendService.saveFriend(terrell, 2);
	         
	         
// 	         friendService.getFriends(terrell);
// 	        //  List<User> t = friendService.getFriends(terrell);
// 	         System.out.println("Terrell's Friends");
// //	         User n = t.get(0);
// 	         System.out.println(t);
	         //	     t.forEach(System.out::println);
//	         System.out.println(friendService.getFriends(terrell));
	         
//	         friendService.saveFriend(anbers, );

	         
	         
//	         averagesizedRod.getFriends().add(jonah);
	         
		     
	         
//	         friendRepo.saveAll(Arrays.asList(jonah, steve));

	         
	         
//	 		add Friend references User
//	         steve.getUsers().add(anbers);  
//	         jonah.getUsers().add(averagesizedRod);
	         
	     	//saving all users
	         
		};
	}

}
