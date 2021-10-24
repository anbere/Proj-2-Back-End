package com.example.demo.user;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.account.Account;
import com.example.demo.friends.Friend;
import com.example.demo.friends.FriendRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"account"})
@EqualsAndHashCode(exclude = {"account"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
	
//	private UserRepository userRepo;
//	private FriendRepository friendRepo;

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
			)
	private Long id;
	@Column(unique=true)
	private String email;
	@Column(unique=true)
	private String username;
	private String password;
	private String firstName;
	private String lastName;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "account_id")
	private Account account;
	
	
//	@JsonIgnore
//	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
//	@JoinColumn(name = "friend_id")
//	private Friend friend;
//	
	

//	
//	@JsonIgnore
//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
//	private Set<Friend> friends = new HashSet<>();


	public User(String username, String password, String email, String firstName,
			String lastName) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

//	@Override
//	public String toString() {
//		return "User{" +
//				"id=" + id +
//				", email='" + email + '\'' +
//				", username='" + username + '\'' +
//				", password='" + password + '\'' +
//				", firstName='" + firstName + '\'' +
//				", lastName='" + lastName + '\'' +
//				'}';
//	}

	
	
	
//	public void addFriend(String friend1, String friend2 ) {
//		Optional<User> user1Exist = userRepo.findByUsername(friend1);
//		Optional<User> user2Exist = userRepo.findByUsername(friend2);
//
//		
//		if(user1Exist.isPresent() && user2Exist.isPresent()){
//			Friend createFriend = new Friend();
//
//			createFriend.setUsers(user1Exist);
//			
//			
//			
//			
//		}
//		
//		
//	}

}
