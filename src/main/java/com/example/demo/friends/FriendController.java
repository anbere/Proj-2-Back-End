package com.example.demo.friends;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/friends")
public class FriendController {
	
//	@Autowired
//	private FriendRepository friendRepo;
	
	private final FriendService friendService;
	
	
	@Autowired
	public FriendController(FriendService friendService) {
		
		this.friendService = friendService;
	}
	
	
	@PostMapping
	public ResponseEntity<List<User>> getFriends(@RequestBody User user){
		System.out.println("Below is User from FriendController");
		System.out.println(user);
		return ResponseEntity.ok(friendService.getFriends(user));
	}

	@PostMapping("/{username}")
	public ResponseEntity<User> addFriend(@RequestBody User user, @PathVariable String username ){
		
		try{
			System.out.println("This is the USER Friend Controller: ");
			System.out.println(user);
			
			System.out.println("This is the USERNAME Friend Controller: ");
			System.out.println(username);
			
			
			User addNewFriend = friendService.saveFriend(user, username);

			return ResponseEntity.ok(addNewFriend);
			
		}catch(IllegalStateException e){
			System.out.println(e.getMessage());
			return ResponseEntity.ok(new User());
			
		}
		
	}

}
