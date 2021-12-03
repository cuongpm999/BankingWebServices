package vn.ptit.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.CreatedBankAccount;
import vn.ptit.entities.DepositAccount;
import vn.ptit.repositories.CreatedBankAccountRepository;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.services.DepositAccountService;
import vn.ptit.utils.HelperCreateBankAccount;

@RestController
@RequestMapping("/rest/api/deposit-account")
public class DepositAccountController {
	@Autowired
	DepositAccountRepository depositAccountRepository;
	@Autowired
	DepositAccountService depositAccountService;
	@Autowired
	CreatedBankAccountRepository createdBankAccountRepository;

	@GetMapping("/find-by-customer/{id}")
	public List<DepositAccount> findByCustomer(@PathVariable("id") int id) {
		return depositAccountService.findByCustomerId(id);
	}

	@PostMapping("/insert")
	@Transactional
	public DepositAccount insert(@RequestBody HelperCreateBankAccount helperCreateBankAccount) {
		DepositAccount depositAccount = helperCreateBankAccount.getDepositAccount();
		CreatedBankAccount createdBankAccount = helperCreateBankAccount.getCreatedBankAccount();

		depositAccount = depositAccountRepository.save(depositAccount);
		createdBankAccount = createdBankAccountRepository.save(createdBankAccount);

		return depositAccount;
	}

	@DeleteMapping(value = "/delete-by-id/{id}")
	public void deleteById(@PathVariable("id") String id) {
		DepositAccount depositAccount = depositAccountRepository.findById(id).get();
		depositAccount.setStatus(false);
		depositAccountRepository.save(depositAccount);
	}

	@GetMapping(value = "/find-by-id/{id}")
	public DepositAccount findById(@PathVariable("id") String id) {
		return depositAccountService.findByIdAndStatusTrue(id);
	}

	@PostMapping("/update")
	public DepositAccount update(@RequestBody DepositAccount depositAccount) {
		depositAccount = depositAccountRepository.save(depositAccount);
		return depositAccount;
	}

	@GetMapping("/find-all")
	public List<DepositAccount> findAll() {
		return depositAccountService.findAllAndStatusTrue();
	}

	@GetMapping("/count/{id}")
	public Boolean countDepositAccountByCustomer(@PathVariable("id") int id) {
		int cnt = depositAccountRepository.countDepositAccountByCustomer(id);
		if (cnt >= 3)
			return false;
		return true;
	}

}
