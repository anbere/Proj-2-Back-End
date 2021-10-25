package com.example.demo.friends;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.demo.transaction.Transaction;
import com.example.demo.user.User;

import lombok.*;

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
	
	
}
