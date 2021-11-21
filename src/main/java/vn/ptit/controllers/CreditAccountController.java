package vn.ptit.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.CreatedBankAccount;
import vn.ptit.entities.CreditAccount;
import vn.ptit.entities.Customer;
import vn.ptit.repositories.CreatedBankAccountRepository;
import vn.ptit.repositories.CreditAccountRepository;
import vn.ptit.services.CreditAccountService;
import vn.ptit.utils.HelperCreateBankAccount;

@RestController
@RequestMapping("/rest/api/credit-account")
public class CreditAccountController {
	@Autowired CreditAccountService creditAccountService;
	@Autowired CreditAccountRepository creditAccountRepository;
	@Autowired CreatedBankAccountRepository createdBankAccountRepository;
	
	@GetMapping("/find-by-customer/{id}")
	public List<CreditAccount> findByCustomer(@PathVariable("id") int id){
		return creditAccountService.findByCustomerId(id);
	}
	
	@PostMapping("/insert")
	@Transactional
	public CreditAccount insert(@RequestBody HelperCreateBankAccount helperCreateBankAccount){
		CreditAccount creditAccount = helperCreateBankAccount.getCreditAccount();
		CreatedBankAccount createdBankAccount = helperCreateBankAccount.getCreatedBankAccount();
		
		creditAccount = creditAccountRepository.save(creditAccount);
		createdBankAccount = createdBankAccountRepository.save(createdBankAccount);
		
		return creditAccount;
	}
	
	@DeleteMapping(value = "/delete-by-id/{id}")
	public void deleteById(@PathVariable("id") String id) {
		CreditAccount creditAccount = creditAccountRepository.findById(id).get();
		creditAccount.setStatus(false);
		creditAccountRepository.save(creditAccount);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public CreditAccount findById(@PathVariable("id") String id) {
		return creditAccountService.findByIdAndStatusTrue(id);
	}
	
	@PostMapping("/update")
	public CreditAccount update(@RequestBody CreditAccount creditAccount){
		creditAccount = creditAccountRepository.save(creditAccount);
		return creditAccount;
	}

}