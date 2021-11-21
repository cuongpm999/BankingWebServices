package vn.ptit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.BankAccountType;
import vn.ptit.repositories.BankAccountTypeRepository;
import vn.ptit.services.BankAccountTypeService;

@RestController
@RequestMapping("/rest/api/bank-account-type")
public class BankAccountTypeCotronller {
	@Autowired BankAccountTypeRepository bankAccountTypeRepository;
	@Autowired BankAccountTypeService bankAccountTypeService;
	
	@GetMapping("/find-all")
	public List<BankAccountType> findAll(){
		return bankAccountTypeService.findAllByStatusTrue();
	}
	
}
