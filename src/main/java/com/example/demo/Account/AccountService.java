package com.example.demo.Account;

import com.example.demo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts()
    {
        return accountRepository.findAll();
    }

    public Account addNewAccount(Account account)
    {
        accountRepository.save(account);
        return account;
    }

    public void deleteAccount(Long accountId)
    {

    }
}
