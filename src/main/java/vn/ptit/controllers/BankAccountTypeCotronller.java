package vn.ptit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.BankAccountType;
import vn.ptit.repositories.BankAccountTypeRepository;
import vn.ptit.services.BankAccountTypeService;

@RestController
@RequestMapping("/rest/api/bank-account-type")
public class BankAccountTypeCotronller {
	@Autowired
	BankAccountTypeRepository bankAccountTypeRepository;
	@Autowired
	BankAccountTypeService bankAccountTypeService;

	@GetMapping("/find-all")
	public List<BankAccountType> findAll() {
		return bankAccountTypeService.findAllByStatusTrue();
	}

	@PostMapping("/insert")
	public BankAccountType insert(@RequestBody BankAccountType bankAccountType) {
		return bankAccountTypeRepository.save(bankAccountType);
	}

	@GetMapping("/find-by-id/{bankAccountTypeID}")
	public BankAccountType findById(@PathVariable("bankAccountTypeID") int bankAccountTypeID) {
		return bankAccountTypeService.findByIdAndStatusTrue(bankAccountTypeID);
	}

	@DeleteMapping("/delete-by-id/{bankAccountTypeID}")
	public void deleteById(@PathVariable("bankAccountTypeID") int bankAccountTypeID){
		BankAccountType bankAccountType = bankAccountTypeRepository.findById(bankAccountTypeID).get();
		bankAccountType.setStatus(false);
		bankAccountTypeRepository.save(bankAccountType);
	}
}
