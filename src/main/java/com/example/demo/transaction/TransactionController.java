package com.example.demo.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/v1/transaction")

public class TransactionController {
	
	@Autowired
	private TransactionRepository transactionRepo;
	
//	public TransactionController(TransactionService transactionService) {
//		System.out.println("transaction service constructor");
//		this.transactionService = transactionService;
//	}
	
	
	@GetMapping
	public ResponseEntity<List<Transaction>> findAllTransactions() {
		List<Transaction> all = transactionRepo.findAll();
		
		if(all.isEmpty()) {
			return null;
		}
		return ResponseEntity.ok(all);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Transaction> findById(@PathVariable("id") Long id) {
		Optional<Transaction> optional = transactionRepo.findById(id);
		
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping
	public ResponseEntity<Transaction> insert(@RequestBody Transaction transaction) {
		Long id = transaction.getId();
		
		if(id != 0) {
			return ResponseEntity.badRequest().build();
		}
		
		transactionRepo.save(transaction);
		return ResponseEntity.status(201).body(transaction);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Transaction> delete(@PathVariable("id") Long id) {
		Optional<Transaction> option = transactionRepo.findById((long) id);

		if(option.isPresent()) {
			transactionRepo.delete(option.get());
			return ResponseEntity.accepted().body(option.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
	
	
	
	
	

}
