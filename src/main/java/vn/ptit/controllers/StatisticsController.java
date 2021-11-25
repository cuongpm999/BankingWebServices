package vn.ptit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.CustomerCreditStat;
import vn.ptit.entities.CustomerDepositStat;
import vn.ptit.entities.CustomerTransactionStat;
import vn.ptit.repositories.BankAccounnRepository;
import vn.ptit.repositories.CustomerRepository;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;
import vn.ptit.services.CustomerService;
import vn.ptit.services.TransactionService;

@RestController
@RequestMapping("/rest/api/statistics")
public class StatisticsController {
	@Autowired TransactionRepository transactionRepository;
	@Autowired CustomerRepository customerRepository;
	@Autowired BankAccounnRepository bankAccounnRepository;
	@Autowired DepositAccountRepository depositAccountRepository;
	@Autowired CustomerService customerService;
	@Autowired TransactionService transactionService;
	
	@GetMapping("/count-trasaction")
	public int countTransaction() {
		return transactionRepository.countTransaction();
	}
	
	@GetMapping("/count-customer")
	public int countCustomer() {
		return customerRepository.countCustomer();
	}
	
	@GetMapping("/count-bank-account")
	public int countBankAccount() {
		return bankAccounnRepository.countBankAccount();
	}
	
	@GetMapping("/total-deposit")
	public Integer totalDeposit() {
		return depositAccountRepository.totalDeposit();
	}
	
	@GetMapping("/get-customer-deposit-stat")
	public List<CustomerDepositStat> getCustomerDepositStat() {
		return customerService.findAllWithBalanceInDepositMax();
	}
	
	@GetMapping("/get-customer-credit-stat")
	public List<CustomerCreditStat> getCustomerCreditStat() {
		return customerService.findAllWithBalanceInCreditMax();
	}
	
	@PostMapping("/get-customer-transaction-stat")
	public List<CustomerTransactionStat> getCustomerTransactionStat(@RequestBody List<String> truyVan) {
		return transactionService.findAllWithTransactionInMonth(truyVan.get(0), truyVan.get(1), truyVan.get(2));
	}

}
