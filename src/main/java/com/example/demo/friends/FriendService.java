package com.example.demo.friends;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

@Service
public class FriendService {
	
	private final FriendRepository friendRepository;
	private final UserRepository userRepository;
	
		
	@Autowired
	public FriendService(FriendRepository friendRepository, UserRepository userRepository) {
		this.friendRepository = friendRepository;
		this.userRepository = userRepository;
	}

	public User saveFriend(User userOrigin, String username) throws NullPointerException{
		
		System.out.println("Do we get inside saveFriend FriendService");

        System.out.println("After User");

        Friend friend = new Friend();
        
        User user1 = userRepository.findByUsername(userOrigin.getUsername()).get();
        
        Optional<User> user2Check = userRepository.findByUsername(username);
        if(!user2Check.isPresent()) {
        	throw new IllegalStateException("Username not found!");
        }
        
        User user2 = user2Check.get();
        User firstUser = user1;
        User secondUser = user2;
        
       
        if(user1.getId() > user2.getId()){
             firstUser = user2;
             secondUser = user1;
        }
        if( !(friendRepository.existsByFirstUserAndSecondUser(firstUser,secondUser)) ){
            friend.setDate(new Date());
            friend.setFirstUser(firstUser);
            friend.setSecondUser(secondUser);
            System.out.println("Before saving friend");
            friendRepository.save(friend);
            System.out.println("Below is friend");
            System.out.println(friend.toString());
            return secondUser;
        }
        else {
        	throw new IllegalStateException("Friend has already been added!");
        }
    }
	
	
	@Transactional
	public List<User> getFriends(User user){
        User currentUser = userRepository.findByUsername(user.getUsername()).get();
        List<Friend> friendsByFirstUser = friendRepository.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepository.findBySecondUser(currentUser);
        System.out.println("First User");
        System.out.println(friendsByFirstUser);
        System.out.println("Second User");
        System.out.println(friendsBySecondUser);
        List<User> friendUsers = new ArrayList<>();

        /*
            suppose there are 3 users with id 1,2,3.
            if user1 add user2 as friend database record will be first user = user1 second user = user2
            if user3 add user2 as friend database record will be first user = user2 second user = user3
            it is because of lexicographical order
            while calling get friends of user 2 we need to check as a both first user and the second user
         */
        for (Friend friend : friendsByFirstUser) {
            friendUsers.add(userRepository.getById(friend.getSecondUser().getId()));
            
        }
        for (Friend friend : friendsBySecondUser) {
            System.out.println(userRepository.getById(2L));
            friendUsers.add(userRepository.getById(friend.getFirstUser().getId()));
        }
        System.out.println("Before returning getFriends");
        System.out.println(friendUsers);
        return friendUsers;

    }
	
	
	
	

	
	


}
