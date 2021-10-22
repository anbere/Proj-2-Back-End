package com.example.demo.user;

import java.util.HashMap;
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
	  System.out.println(user);

      try {
        Optional<User> loggingUser = userService.LoginCheck(user.getUsername(), user.getPassword());
        System.out.println(loggingUser.get());
        User responseUser = loggingUser.get();
        responseUser.setPassword(null);
        return ResponseEntity.ok(responseUser);
      }catch(IllegalStateException e)
      {
        System.out.println(e.getMessage());
        return ResponseEntity.ok(new User());
      }
  }

  @PostMapping
  public ResponseEntity<HashMap<String, Object>> registerNewUser(@RequestBody User user)
  {
    HashMap<String, Object> response = new HashMap<>();

    try {
      userService.addNewUser(user);
      response.put("success", true);
      response.put("message", "");
      return ResponseEntity.ok(response);
    }catch(IllegalStateException e) {
      response.put("success", false);
      response.put("message", e.getMessage());
      return ResponseEntity.ok(response);
    }
  }

  @PutMapping
  public ResponseEntity<HashMap<String, Object>> updateUserDetails(@RequestBody User user) {
    HashMap<String, Object> response = new HashMap<>();

    try{
      System.out.println("From user controller update: " + user);
      User userUpdate = userService.updateUserDetails(user);
      response.put("success", true);
      response.put("message", "");
      return ResponseEntity.ok(response);
    }catch(IllegalStateException e) {
      response.put("success", false);
      response.put("message", e.getMessage());
      return ResponseEntity.ok(response);
    }
  }
}
