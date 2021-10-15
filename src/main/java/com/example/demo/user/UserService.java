package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import com.example.demo.Account.Account;
import com.example.demo.Account.AccountRepository;
import com.example.demo.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //same as component
public class UserService {
  private final UserRepository userRepository;


  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public void addNewUser(User user) {
    Optional<User> userByUsername = userRepository.findUserByUsername(user.getUsername());
    Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
    if(userByUsername.isPresent()) {
      throw new IllegalStateException("Username is taken");
    }else if(userByEmail.isPresent()) {
      throw new IllegalStateException("Email is taken");
    }

    System.out.println(user);
  }
}
