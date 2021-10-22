package com.example.demo.user;

import com.example.demo.account.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@ToString(exclude = {"account"})
@EqualsAndHashCode(exclude = {"account"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {

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

  public User(String username, String password, String email, String firstName,
      String lastName) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(Long id, String username, String email, String firstName, String lastName) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
