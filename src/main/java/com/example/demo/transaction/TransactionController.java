package com.example.demo.transaction;

import com.example.demo.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Transaction>> getTransactions(){
        return ResponseEntity.ok(transactionService.getTransactions());
    }

    //USING THIS FOR DISPLAYING ALL TRANSACTIONS OF CURRENT USER
    @GetMapping(path = "/{username}")
    public ResponseEntity<List<Transaction>> getUserTransactions(@PathVariable String username)
    {
        List<Transaction> transactionList = transactionService.getUserTransactions(username);
        return ResponseEntity.ok(transactionList);
    }

    @GetMapping(path = "/{type}/{username}")
    public ResponseEntity<List<Transaction>> getRequests(@PathVariable String type, @PathVariable String username)
    {
        List<Transaction> transactionList = transactionService.getRequests(type, username);
        return ResponseEntity.ok(transactionList);
    }

    
    //FOR MAKING A PAYMENT TRANSACTION
    @PostMapping(path = "/pay/{origin}/{destination}")
    public ResponseEntity<Transaction> payTransaction(@RequestBody Transaction transaction, @PathVariable String origin, @PathVariable String destination)
    {
        System.out.println("Reached pay Transaction");
        System.out.println("Transaction from pay req: " + transaction + " Origin : " + origin + " destination : " + destination);
        Transaction transactionResponseEntity = transactionService.payTransaction(transaction, origin, destination);
        return ResponseEntity.ok(transactionResponseEntity);
    }

    @PostMapping(path = "/deposit/{username}")
    public ResponseEntity<Account> deposit(@RequestBody Transaction transaction, @PathVariable String username)
    {
        //System.out.println("transcation: " + transaction + "\nusername: " + username);
        try {
            Account acc = transactionService.deposit(transaction, username);
            return ResponseEntity.ok(acc);
        }catch(IllegalStateException e)
        {
            System.out.println(e.getMessage());
            return ResponseEntity.ok( new Account() );
        }

    }

    @PostMapping(path = "/request/{origin}/{destination}")
    public ResponseEntity<Transaction> requestTransaction(@RequestBody Transaction transaction, @PathVariable String origin, @PathVariable String destination)
    {
        Transaction tr = transactionService.requestTransaction(transaction, origin, destination);
        return ResponseEntity.ok(tr);
    }

    @PutMapping(path ="/accept/{choice}")
    public ResponseEntity<Transaction> acceptRequestTransaction (@RequestBody Transaction transaction, @PathVariable String choice)
    {
        Transaction tr = transactionService.acceptRequestTransaction(transaction, choice);
        return ResponseEntity.ok(tr);
    }

}
