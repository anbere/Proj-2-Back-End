package com.example.demo.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/user")
public class UserController {

  private final UserService userService;

  @Autowired //userService above will be instantiated and injected into this constructor
  public UserController(UserService userService)
  {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getUsers() {
    return userService.getUsers();
  }

  @PostMapping(path="/login")
  public void loginCheck(@RequestBody User user) {
      System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
  }

  @PostMapping
  public void registerNewUser(@RequestBody User user)
  {
    userService.addNewUser(user);
//    System.out.println("new user");
  }
}
