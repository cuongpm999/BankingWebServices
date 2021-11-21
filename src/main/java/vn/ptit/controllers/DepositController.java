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
import vn.ptit.services.DepositService;

@RestController
@RequestMapping("/rest/api/deposit")
public class DepositController {
	@Autowired DepositService depositService;
	@Autowired TransactionRepository transactionRepository;
	
	@GetMapping("/find-by-deposit-account/{id}")
	public List<Transaction> findByDepositAccount(@PathVariable("id") String id){
		return depositService.findByDepositAccount(id);
	}
	
	@PostMapping(value = "/insert")
	public Transaction insert(@RequestBody Transaction transaction) {
		return transactionRepository.save(transaction);
	}
}
