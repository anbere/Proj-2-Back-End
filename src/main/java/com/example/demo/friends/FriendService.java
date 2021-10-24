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
	
	private final FriendRepository friendRepo;
	private final UserRepository userRepo;
	
		
	@Autowired
	public FriendService(FriendRepository friendRepo, UserRepository userRepo) {
		this.friendRepo = friendRepo;
		this.userRepo = userRepo;
	}
	
	
//	public List<Friend> getFriends(){
//		return friendRepo.findAll();
//	}
	
	public void saveFriend(User userOrigin, long id) throws NullPointerException{
		
		System.out.println("Do we get inside saveFriend FriendService");
		
//        User user = userRepo.getById(id);
		
        
        System.out.println("After User");
        
//        UserDto userDto2 = modelMapper.map(user,UserDto.class);

        
        
        Friend friend = new Friend();
        User user1 = userRepo.findByUsername(userOrigin.getUsername()).get();
        
        User user2 = userRepo.findById(id).get();
        User firstuser = user1;
        User seconduser = user2;
        
       
        if(user1.getId() > user2.getId()){
             firstuser = user2;
             seconduser = user1;
        }
        if( !(friendRepo.existsByFirstUserAndSecondUser(firstuser,seconduser)) ){
            friend.setDate(new Date());
            friend.setFirstUser(firstuser);
            friend.setSecondUser(seconduser);
            System.out.println("Before saving friend");
            friendRepo.save(friend);
            System.out.println("Below is friend");
            System.out.println(friend.toString());
        }
    }
	
	
	@Transactional
	public List<User> getFriends(User user){
        User currentUser = userRepo.findByUsername(user.getUsername()).get();
        List<Friend> friendsByFirstUser = friendRepo.findByFirstUser(currentUser);
        List<Friend> friendsBySecondUser = friendRepo.findBySecondUser(currentUser);
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
            friendUsers.add(userRepo.getById(friend.getSecondUser().getId()));
            
        }
        for (Friend friend : friendsBySecondUser) {
            System.out.println(userRepo.getById(2L));
            friendUsers.add(userRepo.getById(friend.getFirstUser().getId()));
        }
        System.out.println("Before returning getFriends");
        System.out.println(friendUsers);
        return friendUsers;

    }
	
	
	
	

	
	

}
