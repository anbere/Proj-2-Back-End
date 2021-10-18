package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<User> loginCheck(@RequestBody User user) {

      try {
        Optional<User> loggingUser = userService.LoginCheck(user.getUsername(), user.getPassword());
        System.out.println(loggingUser.get());
        return ResponseEntity.ok(loggingUser.get());
      }catch(IllegalStateException e)
      {
        System.out.println(e.getMessage());
        return ResponseEntity.ok(new User());
      }

  }

  @PostMapping
  public void registerNewUser(@RequestBody User user)
  {
    userService.addNewUser(user);
//    System.out.println("new user");
  }
}
