package com.example.demo.friends;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.user.User;

@Repository 
public interface FriendRepository extends JpaRepository<Friend, Long>{

	
    boolean existsByFirstUserAndSecondUser(User first,User second);

    List<Friend> findByFirstUser(User user);
    List<Friend> findBySecondUser(User user);
    
    
//	Optional<Friend> findByUsername(String user);
	

}
