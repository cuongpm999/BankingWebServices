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
import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.CreditAccountRepository;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.CreatePaymentService;
import vn.ptit.services.CreditAccountService;
import vn.ptit.services.DepositAccountService;

@RestController
@RequestMapping("/rest/api/create-payment")
public class CreatePaymentController {
	@Autowired CreatePaymentService createPaymentService;
	@Autowired TransactionRepository transactionRepository;
	@Autowired DepositAccountService depositAccountService;
	@Autowired CreditAccountService creditAccountService;
	@Autowired CreditAccountRepository creditAccountRepository;
	@Autowired DepositAccountRepository depositAccountRepository;
	
	@PostMapping("/find-payment-account/{id}")
	public List<Transaction> findById(@PathVariable("id") String id, @RequestBody Map<String, Object> map){
		return createPaymentService.findById(id, map);
	}
	
	@PostMapping("/insert")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Boolean insertPayment(@RequestBody Transaction transaction) {
		DepositAccount depositAccount = depositAccountService.findByIdAndStatusTrue(transaction.getDepositAccount().getId());
		CreditAccount creditAccount = creditAccountService.findByIdAndStatusTrue(transaction.getCreditAccount().getId());
		double afterBalanceCredit = creditAccount.getBalance() - transaction.getMoney();
		if(afterBalanceCredit<0) {
			transaction.setMoney(transaction.getMoney() + afterBalanceCredit);
			afterBalanceCredit = 0;
		}
		double afterBalanceDeposit = depositAccount.getBalance() - transaction.getMoney();
		if(afterBalanceDeposit<depositAccount.getMinimumBalance()) {
			return false;
		}
		depositAccount.setBalance(afterBalanceDeposit);
		creditAccount.setBalance(afterBalanceCredit);
		creditAccountRepository.save(creditAccount);
		depositAccountRepository.save(depositAccount);
		transaction.setCreditAccount(creditAccount);
		transaction.setDepositAccount(depositAccount);
		transaction.setType("PAYMENT");
		transaction.setAfterBalanceCredit(afterBalanceCredit);
		transaction.setAfterBalanceDeposit(afterBalanceDeposit);
		transactionRepository.save(transaction);
		
		return true;
	}
	
	@GetMapping("/find-transaction-by-id/{id}")
	public Transaction findById(@PathVariable("id") int id){
		return transactionRepository.findById(id).get();
	}
}
