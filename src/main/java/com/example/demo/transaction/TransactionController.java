package com.example.demo.transaction;

import com.example.demo.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/transaction")
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

//	@GetMapping(path = "{id}")
//	public ResponseEntity<List<Transaction>> getTransactionForUser(@PathVariable Long id) {
//		return
//	}


	@PostMapping(path = "/deposit/{username}")
	public ResponseEntity<Account> deposit(@RequestBody Transaction transaction, @PathVariable String username)
	{
		System.out.println("transaction: " + transaction + "\nusername: " + username);
		try{
			Account updatedAccount = transactionService.deposit(transaction, username);
			return ResponseEntity.ok(updatedAccount);
		}catch(IllegalStateException e)
		{
			System.out.println(e.getMessage());
			return ResponseEntity.ok(new Account());
		}
	}

}
