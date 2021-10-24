package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import com.example.demo.account.Account;
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
  
  

  public Optional<User> LoginCheck(String username, String password) {
    Optional<User> userByUsername = userRepository.findByUsername(username);
    if(userByUsername.isPresent() && userByUsername.get().getPassword().equals(password)){
        System.out.println("From userservice: " + userByUsername.get());
        return userByUsername;
      }
    throw new IllegalStateException("User not found");
  }
  
  

  public void addNewUser(User user) {
    Optional<User> userByUsername = userRepository.findByUsername(user.getUsername());
    Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
    if(userByUsername.isPresent()) {
      throw new IllegalStateException("Username is taken");
    }else if(userByEmail.isPresent()) {
      throw new IllegalStateException("Email is taken");
    }

    Account account = new Account();
    user.setAccount(account);

    userRepository.save(user);
    System.out.println(user);
  }
}
