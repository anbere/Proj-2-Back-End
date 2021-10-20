package com.example.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/home")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired //TransactionService above will be instantiated and injected into this constructor
    public TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    /*@PostMapping
    public ResponseEntity<Transaction> insertTransaction() {


       transactionService.addNewTransaction();

        return ResponseEntity.status(201).body(transaction);
        //check if users are valid(origin and destination)
        //user input validation
        *//*try {
            Optional<Transaction> transaction1 = transactionService.addNewTransaction();
            System.out.println(loggingUser.get());
            return ResponseEntity.ok();
        }catch(IllegalStateException e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.ok(new User());
        }

    }*//*
    }*/

}
