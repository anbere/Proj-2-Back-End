package com.example.demo.friends;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

@SpringBootApplication
public class FriendConfig {
	
	
//	@Bean
//	CommandLineRunner commandLineRunner3(FriendRepository friendRepo) {
//		return args -> {
//			
//		}
//	}
	
	
	
	
	
	@Autowired
	private FriendRepository friendRepository;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run( FriendConfig.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception{
		Friend jonah = new Friend("Jonah", "Hollond", "jnah", null);
		Friend steve = new Friend("Steve", "Rogers", "cap", null);
		
		User rebec = new User("rebec", "password", "rebec@gmail.com", "Rebecca", "Bam");
		User sebs = new User("sebs", "pass", "sebast@gmail.com", "Sebastian", "Stan");
	
		
		//add Friend references User
		jonah.getUsers().add(rebec);
		jonah.getUsers().add(sebs);
		
		
		//add User references Friend
		rebec.getFriends().add(jonah);
		sebs.getFriends().add(steve);
		
		
		this.friendRepository.save(jonah);
		
	
	
	
	
	}
	
	

}
