package vn.ptit.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.CreditAccount;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.CreditAccountRepository;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.CreditAccountService;
import vn.ptit.services.CreditService;

@RestController
@RequestMapping("/rest/api/credit")
public class CreditController {
	@Autowired CreditService creditService;
	@Autowired TransactionRepository transactionRepository;
	@Autowired CreditAccountRepository creditAccountRepository;
	@Autowired CreditAccountService creditAccountService;
	
	@PostMapping("/find-by-credit-account/{id}")
	public List<Transaction> findByCreditAccount(@PathVariable("id") String id, @RequestBody Map<String, Object> map){
		return creditService.findByCreditAccount(id, map);
	}
	
	@PostMapping(value = "/insert")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Boolean insert(@RequestBody Transaction transaction) {
		CreditAccount creditAccount = creditAccountService.findByIdAndStatusTrue(transaction.getCreditAccount().getId());
		double afterBalanceCredit = creditAccount.getBalance()+transaction.getMoney();
		if(afterBalanceCredit>creditAccount.getLimitBalance()) {
			return false;
		}
		transaction.setType("CREDIT");
		transaction.setAfterBalanceCredit(afterBalanceCredit);
		transaction.setCreditAccount(creditAccount);
		creditAccount.setBalance(afterBalanceCredit);
		transactionRepository.save(transaction);
		creditAccountRepository.save(creditAccount);
		return true;
	}
	
	@GetMapping("/find-transaction-by-id/{id}")
	public Transaction findById(@PathVariable("id") int id){
		return transactionRepository.findById(id).get();
	}
}
