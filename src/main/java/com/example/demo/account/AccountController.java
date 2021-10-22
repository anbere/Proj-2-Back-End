package com.example.demo.account;

import com.example.demo.user.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        Long userID = Long.valueOf(id);
        Account account = accountService.getAccount(userID);
        return ResponseEntity.ok(account);
    }

    @PutMapping
    public ResponseEntity<Account> addRoutingNumber(@RequestBody Account account)
    {
        Account acc = accountService.addRoutingNumber(account);
        return ResponseEntity.ok(acc);
    }
}
