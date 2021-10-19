package com.example.demo.transaction;

import com.example.demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired //TransactionService above will be instantiated and injected into this constructor
    public TransactionController(TransactionService transactionService)
    {
        this.transactionService = transactionService;
    }

}
