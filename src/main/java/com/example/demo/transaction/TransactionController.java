//package com.example.demo.transaction;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//import java.util.List;
//
//@RestController
//@CrossOrigin(origins = "http://localhost:3000")
//@RequestMapping(path = "api/v1/transactions")
//public class TransactionController {
//	
//	@Autowired
//	private TransactionRepository transactionRepo;
//	
//    private final TransactionService transactionService;
//
//    
//    @Autowired
//    public TransactionController(TransactionService transactionService)
//    {
//        this.transactionService = transactionService;
//    }
//
//    @GetMapping
//    public List<Transaction> getTransactions(){
//        return transactionService.getTransactions();
//    }
//
//	 
//	
//	@GetMapping("/{findAll}")
//	public ResponseEntity<List<Transaction>> findAllTransactions() {
//		List<Transaction> all = transactionRepo.findAll();
//		
//		if(all.isEmpty()) {
//			return null;
//		}
//		return ResponseEntity.ok(all);
//	}
//	
//	
//	@GetMapping("/{id}")
//	public ResponseEntity<Transaction> findById(@PathVariable("id") Long id) {
//		Optional<Transaction> optional = transactionRepo.findById(id);
//		
//		if(optional.isPresent()) {
//			return ResponseEntity.ok(optional.get());
//		}
//		
//		return ResponseEntity.noContent().build();
//	}
////	
////	
//	@PostMapping
//	public ResponseEntity<Transaction> insert(@RequestBody Transaction transaction) {
//		Long id = transaction.getId();
//		
//		if(id != 0) {
//			return ResponseEntity.badRequest().build();
//		}
//		
//		transactionRepo.save(transaction);
//		return ResponseEntity.status(201).body(transaction);
//	}
//
//	
//	
////	@DeleteMapping("/{id}")
////	public ResponseEntity<Transaction> delete(@PathVariable("id") Long id) {
////		Optional<Transaction> option = transactionRepo.findById((long) id);
////
////		if(option.isPresent()) {
////			transactionRepo.delete(option.get());
////			return ResponseEntity.accepted().body(option.get());
////		}
////		
////		return ResponseEntity.notFound().build();
////	}
////	
//	
//
//
//
//    /*@PostMapping
//    public ResponseEntity<Transaction> insertTransaction() {
//
//
//       transactionService.addNewTransaction();
//
//        return ResponseEntity.status(201).body(transaction);
//        //check if users are valid(origin and destination)
//        //user input validation
//        *//*try {
//            Optional<Transaction> transaction1 = transactionService.addNewTransaction();
//            System.out.println(loggingUser.get());
//            return ResponseEntity.ok();
//        }catch(IllegalStateException e)
//        {
//            System.out.println(e.getMessage());
//            return ResponseEntity.ok(new User());
//        }
//
//    }*//*
//    }*/
//}



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
