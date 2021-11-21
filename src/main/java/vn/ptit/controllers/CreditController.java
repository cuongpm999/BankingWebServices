package vn.ptit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.Transaction;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.CreditService;

@RestController
@RequestMapping("/rest/api/credit")
public class CreditController {
	@Autowired CreditService creditService;
	@Autowired TransactionRepository transactionRepository;
	
	@GetMapping("/find-by-credit-account/{id}")
	public List<Transaction> findByCreditAccount(@PathVariable("id") String id){
		return creditService.findByCreditAccount(id);
	}
	
	@PostMapping(value = "/insert")
	public Transaction insert(@RequestBody Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}
