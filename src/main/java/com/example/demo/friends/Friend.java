package com.example.demo.friends;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "friend")
public class Friend {
	@Id
	@SequenceGenerator(
	            name = "transaction_sequence",
	            sequenceName = "transaction_sequence",
	            allocationSize = 1
	 )
	 @GeneratedValue(
	            strategy = GenerationType.SEQUENCE,
	            generator = "transaction_sequence"
	 )
	
	@Column(name = "friend_id")
	private Long id;
	private String username, firstname, lastname;
	private Date date;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "friend_user",
			joinColumns = {@JoinColumn(name = "friend_id")},
			inverseJoinColumns = {@JoinColumn(name = "user_id")}
			)


	private Set<User> users = new HashSet<>();


	public Friend(String firstname, String lastname, String username,  Date date) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.date = date;
	}
	
	
	
}
