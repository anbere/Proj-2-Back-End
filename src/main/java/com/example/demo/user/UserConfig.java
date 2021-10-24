package com.example.demo.user;

import com.example.demo.account.Account;
import com.example.demo.account.AccountRepository;
import com.example.demo.friends.Friend;
import com.example.demo.friends.FriendRepository;
import com.example.demo.friends.FriendService;
import com.example.demo.transaction.Transaction;
import com.example.demo.transaction.TransactionRepository;
import com.example.demo.transaction.TransactionService;
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
import java.util.Optional;
import java.util.List;

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
			anbers.setAccount(account1);
			account1.setUser(anbers);

			averagesizedRod.setAccount(account2);
			account2.setUser(averagesizedRod);

			//user terrell is set to account 3, and account 3 is set to user terrell
			terrell.setAccount(account3);
			account3.setUser(terrell);
			
			userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, terrell));
//			User u = userRepository.getById(1L);
			
			
			 //add User references Friend
	         friendService.saveFriend(anbers, 2);
	         
	         friendService.saveFriend(terrell, 1);
	         
	         friendService.saveFriend(terrell, 2);
	         
	         
	         friendService.getFriends(terrell);
	         List<User> t = friendService.getFriends(terrell);
	         System.out.println("Terrell's Friends");
//	         User n = t.get(0);
	         System.out.println(t);
	         //	     t.forEach(System.out::println);
//	         System.out.println(friendService.getFriends(terrell));
	         
//	         friendService.saveFriend(anbers, );

	         
	         
//	         averagesizedRod.getFriends().add(jonah);
	         
		     
	         
//	         friendRepo.saveAll(Arrays.asList(jonah, steve));

	         
	         
//	 		add Friend references User
//	         steve.getUsers().add(anbers);  
//	         jonah.getUsers().add(averagesizedRod);
	         
	     	//saving all users
	         

		
			
			
			
			
			
			
			
//			//testing what prints out of user terrell and testing to see if the account gets printed
//			Optional<User> terr = userRepository.findByUsername("T");
//			System.out.println("User: " + terr);
//			System.out.println("User: " + terr.get().getUsername() + "\nAccount Summary: " + terr.get().getAccount());
//			
//			
//
//			System.out.println("Anbers Balance Before: " + anbers.getAccount().getBalance());
//			System.out.println("Terrell Balance Before: " + terrell.getAccount().getBalance());
//
//			Transaction pay = new Transaction("pay", 30, "I had a great time at dinner." );
//			pay.setOrigin(anbers.getAccount());
//			
//			
//		
//			
//			pay.setDestination(terrell.getAccount());
//			
//
//			TransactionService service = new TransactionService(transactionRepository, userRepository);
//			service.addNewTransaction(pay, anbers.getUsername(), terrell.getUsername());
//
//			System.out.println("Anbers Balance After: " + anbers.getAccount().getBalance());
//			System.out.println("Terrell Balance After: (Me)" + terrell.getAccount().getBalance());
//
//			List<Transaction> t = service.getTransactions();
//			System.out.println(t);

			//			anbers.setAccount(account1);
			//			averagesizedRod.setAccount(account2);
			//			User u1 = new User("T", "M", "t@gmail.com", "t", "m");
			//			account3.setUser(u1);
			//			u1.setAccount(account3);
			//			userRepository.saveAll(Arrays.asList(anbers, averagesizedRod, u1));
			//
			//			Transaction transaction = new Transaction("pay", 200, "This is working");
			//			Transaction deposit = new Transaction(500);
			//
			//			TransactionService service = new TransactionService(transactionRepository, userRepository, accountRepository);
			//			service.deposit(deposit,"T");
			//
			//			transactionRepository.save(deposit);
			////			TransactionService service = new TransactionService(transactionRepository);
			//			service.deposit(deposit, "anbers");
			//
			//			System.out.println("accountId: " + userRepository.findByUsername("anders"));
			//			System.out.println("everything: " + anbers);
			//			System.out.println(anbers.getAccount());
			//
			//			Transaction transaction1 = new Transaction("pending", 75.32, "This is working");
			//			transaction1.setOrigin(account);
			//			transaction1.setDestination(account2);
			//			accountRepository.saveAll(Arrays.asList(account, account2));
			//			transactionRepository.save(transaction1);
			//			Optional<Account> account3 = accountRepository.findById(1L);
			//			Optional<Account> account4 = accountRepository.findById(2L);
			//			System.out.println(account3.get().getOrigin() + "\n" + account4.get().getDestination());
			//			System.out.println("account 3 " + account3);
		};
	}

}
