package com.example.demo.transaction;

import com.example.demo.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;


public class TransactionConfig {
    @Bean
    CommandLineRunner commandLineRunner2(TransactionRepository transactionRepository) {
        return null;
    }
}
