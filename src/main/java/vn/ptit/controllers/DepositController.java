package vn.ptit.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.DepositAccountService;
import vn.ptit.services.DepositService;

@RestController
@RequestMapping("/rest/api/deposit")
public class DepositController {
	@Autowired DepositService depositService;
	@Autowired TransactionRepository transactionRepository;
	@Autowired DepositAccountRepository depositAccountRepository;
	@Autowired DepositAccountService depositAccountService;
	
	@GetMapping("/find-by-deposit-account/{id}")
	public List<Transaction> findByDepositAccount(@PathVariable("id") String id){
		return depositService.findByDepositAccount(id);
	}
	
	@PostMapping(value = "/insert")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Boolean insert(@RequestBody Transaction transaction) {
		DepositAccount depositAccount = depositAccountService.findByIdAndStatusTrue(transaction.getDepositAccount().getId());
		double afterBalanceDeposit = depositAccount.getBalance()+transaction.getMoney();
		transaction.setDepositAccount(depositAccount);
		transaction.setType("DEPOSIT");
		transaction.setAfterBalanceDeposit(afterBalanceDeposit);
		depositAccount.setBalance(afterBalanceDeposit);
		transactionRepository.save(transaction);
		depositAccountRepository.save(depositAccount);
		return true;
	}
	
	@GetMapping("/find-transaction-by-id/{id}")
	public Transaction findById(@PathVariable("id") int id){
		return transactionRepository.findById(id).get();
	}
	
}
