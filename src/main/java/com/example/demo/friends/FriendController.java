package com.example.demo.friends;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.user.User;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/friends")
public class FriendController {
	
	@Autowired
	private FriendRepository friendRepo;
	
	private final FriendService friendService;
	
	
	@Autowired
	public FriendController(FriendService friendService) {
		this.friendService = friendService;
	}
	
	
	@GetMapping
	public List<User> getFriends(User user){
		return friendService.getFriends(user);
		
	}
	
	
	@PostMapping("/addFriend")
	public ResponseEntity<Friend> addFriend(@RequestBody Friend friend ){
		
		return null;
//		Long id = friend.getId();
		
		
		
		
//		if(id !=0) {
//			return ResponseEntity.badRequest().build();
//		}
//		
//		
//		
//		friendRepo.save(friend);
//		return ResponseEntity.status(201).body(friend);
	}
	
	
	
	

}
