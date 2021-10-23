package com.example.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions(){
        return transactionService.getTransactions();
    }

    @PostMapping(path = "/pay/{origin}/{destination}")
    public ResponseEntity<Transaction> payTransaction(@RequestBody Transaction transaction, @PathVariable String origin, @PathVariable String destination)
    {
        ResponseEntity<Transaction> transactionResponseEntity = ResponseEntity.ok(transactionService.payTransaction(transaction, origin, destination));
        return transactionResponseEntity;
    }

    @PostMapping(path = "/deposit/{username}")
    public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction, @PathVariable String username)
    {
        System.out.println("transcation: " + transaction + "\nusername: " + username);
        Transaction tr = transactionService.deposit(transaction, username);
        return ResponseEntity.ok(tr);
        //ResponseEntity<Transaction>
    }

}
