//package com.example.demo.friends;
//
//import java.util.Arrays;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.example.demo.user.User;
//import com.example.demo.user.UserRepository;
//
//@Configuration
//public class FriendConfig {
//	
////	@Autowired
////	private FriendRepository friendRepository;
////	
////	
////	@SuppressWarnings("unused")
////	@Autowired
////	private UserRepository userRepository;
//	
//	@Bean
//	CommandLineRunner commandLineRunner3(FriendRepository friendRepo, UserRepository userRepository) {
//		return args -> {
//		     User anbers = new User(
//		             "anbers",
//		             "password",
//		             "andres@gmail.com",
//		             "Andres",
//		             "Rodriguez"
//		         );
//
//		         User averagesizedRod = new User(
//		             "averagesizedRod",
//		             "password",
//		             "rodrigo@gmail.com",
//		             "Rodrigo",
//		             "Flores"
//		         );
//		         
//		 Friend jonah = new Friend(
//				 "Jonah", 
//				 "Hollond",
//				 "jnah", 
//				 null);
//		         
//		 Friend steve = new Friend(
//				 "Steve", 
//				 "Rogers", 
//				 "cap",
//				 null);
//		         
//		  	
//				 //add User references Friend
//		         anbers.getFriends().add(steve);
//		         averagesizedRod.getFriends().add(jonah);
//		         
//		         
//		 		//add Friend references User
//		         steve.getUsers().add(anbers);  
//		         jonah.getUsers().add(averagesizedRod);
//		         
//		         userRepository.saveAll(Arrays.asList(anbers, averagesizedRod));
//		         friendRepo.saveAll(Arrays.asList(jonah, steve));
//		   
//		};
//	}
//	
//	
//	
//	
//	
//
////	
////	
////	public void run(String... args) throws Exception{
////		Friend jonah = new Friend("Jonah", "Hollond", "jnah", null);
////		Friend steve = new Friend("Steve", "Rogers", "cap", null);
////		
////		User rebec = new User("rebec", "password", "rebec@gmail.com", "Rebecca", "Bam");
////		User sebs = new User("sebs", "pass", "sebast@gmail.com", "Sebastian", "Stan");
////	
////		
////		//add Friend references User
////		jonah.getUsers().add(rebec);
////		jonah.getUsers().add(sebs);
////		
////		
////		//add User references Friend
////		rebec.getFriends().add(jonah);
////		sebs.getFriends().add(steve);
////		
////		
////		this.friendRepository.save(jonah);
////	}
//	
//	
//
//}
