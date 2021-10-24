package com.example.demo.friends;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"firstUser", "secondUser"})
@EqualsAndHashCode(exclude = {"firstUser", "secondUser"})
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
	private Date date;
	
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "first_user_id", referencedColumnName = "id")
    User firstUser;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    User secondUser;
	
    
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(
//			name = "friend_user",
//			joinColumns = {@JoinColumn(name = "friend_id")},
//			inverseJoinColumns = {@JoinColumn(name = "user_id")}
//			)
//	private Set<User> users = new HashSet<>();
	
	
	

//
//	public Friend(String firstname, String lastname, String username,  Date date) {
//		super();
//		this.username = username;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.date = date;
//	}
//	
//
//	public Friend(String firstname, Date date, ) {
//		super();
//		this.username = username;
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.date = date;
//	}
	
	
}
