package com.example.demo.friends;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PostMapping
    public ResponseEntity<List<User>> getFriends(@RequestBody User user){
        System.out.println("From friend contoller get friends: " +  .user);
        List<User> friends = friendService.getFriends(user);
        return ResponseEntity.ok(friends);

    }


//    @PostMapping("/addFriend")
//    public ResponseEntity<Friend> addFriend(@RequestBody Friend friend ) {
//
//    }

}
