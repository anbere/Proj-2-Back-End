package com.example.demo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Account getAccount(Long id)
    {
        System.out.println("Reached getAccount in Service");
        Account acc = accountRepository.findById(id).get();
        System.out.println("from acc service get acc: " + acc);
        return acc;
    }

    public Account addNewAccount(Account account)
    {
        accountRepository.save(account);
        return account;
    }

    public Account addRoutingNumber(Account account)
    {
        System.out.println("from account service: " + account);
        Account existing = accountRepository.getById(account.getId());
        existing.setRoutingNumber(account.getRoutingNumber());
        return accountRepository.save(existing);
    }

}
