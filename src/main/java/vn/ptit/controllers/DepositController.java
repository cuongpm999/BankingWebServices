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

import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.DepositAccountService;
import vn.ptit.services.DepositService;
import vn.ptit.services.TransactionService;
import vn.ptit.utils.HelperTransaction;

@RestController
@RequestMapping("/rest/api/deposit")
public class DepositController {
	@Autowired
	DepositService depositService;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	DepositAccountRepository depositAccountRepository;
	@Autowired
	DepositAccountService depositAccountService;
	@Autowired
	TransactionService transactionService;

	@PostMapping("/find-by-deposit-account/{id}")
	public List<Transaction> findByDepositAccount(@PathVariable("id") String id, @RequestBody Map<String, Object> map) {
		return depositService.findByDepositAccount(id, map);
	}

	@PostMapping(value = "/insert")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public HelperTransaction insert(@RequestBody Transaction transaction) {
		DepositAccount depositAccount = depositAccountService
				.findByIdAndStatusTrue(transaction.getDepositAccount().getId());
		double afterBalanceDeposit = depositAccount.getBalance() + transaction.getMoney();
		if (afterBalanceDeposit < depositAccount.getMinimumBalance())
			return new HelperTransaction(0, transaction);
		transaction.setDepositAccount(depositAccount);
		transaction.setType("DEPOSIT");
		transaction.setAfterBalanceDeposit(afterBalanceDeposit);
		depositAccount.setBalance(afterBalanceDeposit);
		transaction = transactionRepository.save(transaction);
		depositAccountRepository.save(depositAccount);
		return new HelperTransaction(1, transaction);
	}

	@GetMapping("/find-transaction-by-id/{id}")
	public Transaction findById(@PathVariable("id") int id) {
		return transactionRepository.findById(id).get();
	}

	@PostMapping("/find-first-transactions-in-month")
	public List<Transaction> findByEmployeeId(@RequestBody List<String> text) {
		return transactionService.findFirstTransactionInMonth(Integer.parseInt(text.get(0)), text.get(1));
	}

	@PostMapping("/find-first-transactions-deposit-account")
	public List<Transaction> findByEmployeeCreatedDepositAccount(@RequestBody List<String> text) {
		return transactionService.findFirstTransactionToManager(Integer.parseInt(text.get(0)), text.get(1));
	}
	
	@PostMapping("/statistic-by-deposit-account/{id}")
	public List<Transaction> statisticByDepositAccount(@PathVariable("id") String id, @RequestBody Map<String, Object> map){
		return depositService.statisticByDepositAccount(id, map);
	}
	
	@PostMapping("/export-with-deposit-account/{id}")
	public List<Transaction> exportWithDepositAccount(@PathVariable("id") String id, @RequestBody Map<String, Object> map){
		return depositService.exportWithDepositAccount(id, map);
	}

}
