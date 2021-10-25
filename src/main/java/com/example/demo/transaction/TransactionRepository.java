package com.example.demo.transaction;

import com.example.demo.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByOrigin(Account account);

    List<Transaction> findByTypeAndDestination(String type, Account account);
}

