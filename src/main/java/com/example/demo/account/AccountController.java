package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class AccountController {
    private final AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService)
    {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    /*@PostMapping(path = "/account")
    public ResponseEntity<Account> viewAccountInfo()
    {

    }*/
}
