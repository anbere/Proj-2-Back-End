package com.example.demo.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "api/transaction")
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
    public  ResponseEntity<Transaction> payTransaction(@RequestBody Transaction transaction, @PathVariable String origin, @PathVariable String destination)
    {
        Transaction transactionResponseEntity = transactionService.payTransaction(transaction, origin, destination);
        return ResponseEntity.ok(transactionResponseEntity);
    }

    @PostMapping(path = "/deposit/{username}")
    public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction, @PathVariable String username)
    {
        System.out.println("transcation: " + transaction + "\nusername: " + username);
        Transaction depositTransaction = transactionService.deposit(transaction, username);
        return ResponseEntity.ok(depositTransaction);
    }

    @PostMapping(path = "/request/{origin}/{destination}")
    public ResponseEntity<Transaction> requestTransaction(@RequestBody Transaction transaction, @PathVariable String origin, @PathVariable String destination)
    {
        ResponseEntity<Transaction> transactionResponseEntity = ResponseEntity.ok(transactionService.requestTransaction(transaction, origin, destination));
        return transactionResponseEntity;
    }

    @PutMapping(path ="/accept/{choice}")
    public ResponseEntity<Transaction> acceptRequestTransaction (@RequestBody Transaction transaction, @PathVariable String choice)
    {
        ResponseEntity<Transaction> transactionResponseEntity = ResponseEntity.ok(transactionService.acceptRequestTransaction(transaction, choice));
        return transactionResponseEntity;
    }

}
