package vn.ptit.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.CreatedBankAccount;
import vn.ptit.entities.Employee;
import vn.ptit.entities.Salary;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.CreatedBankAccountRepository;
import vn.ptit.repositories.SalaryRepository;
import vn.ptit.services.EmployeeService;
import vn.ptit.services.SalaryService;
import vn.ptit.services.TransactionService;

@RestController
@RequestMapping("/rest/api/salary")
public class SalaryController {
	@Autowired SalaryRepository salaryRepository;
	@Autowired SalaryService salaryService;
	@Autowired TransactionService transactionService;
	@Autowired EmployeeService employeeService; 
	@Autowired CreatedBankAccountRepository createdBankAccountRepository;
	
	@GetMapping("/find-by-id/{id}")
	public Salary findById(@PathVariable("id") int salaryId) {
		Optional<Salary> optSalary = salaryRepository.findById(salaryId);
		if(optSalary.isPresent()) {
			return optSalary.get();
		}
		return null;
	}
	
	@GetMapping("/find-all-by-employee/{id}")
	public List<Salary> findSalariesByEmployee(@PathVariable("id") int employeeId){
		return salaryService.findSalariesByEmployee(employeeId);
	}
	
	@PostMapping("/insert/{id}")
	public boolean insert(@RequestBody Salary salary, @PathVariable("id") int employeeId) {
		List<Salary> salaries = salaryService.findSalariesByEmployee(employeeId);
		for (Salary salary2 : salaries) {
			if(salary2.getDateSalary().equalsIgnoreCase(salary.getDateSalary())) return false;
		}
		Employee employee = employeeService.findByIdAndStatusTrue(employeeId);
		salary.setEmployee(employee);
		List<Transaction> transactions = transactionService.findFirstTransactionInMonth(employeeId, salary.getDateSalary());
		double total = 0;
		for (Transaction transaction : transactions) {
			total += transaction.getMoney() * 0.02;
		}
		if(employee.getPosition().equalsIgnoreCase("MANAGER")) {
			transactions = transactionService.findFirstTransactionToManager(employeeId, salary.getDateSalary());
			for (Transaction transaction : transactions) {
				total += transaction.getMoney() * 0.02;
			}
			String datas[] = salary.getDateSalary().split("\\/");
			int month = Integer.parseInt(datas[0]);
			int year = Integer.parseInt(datas[1]);
			List<CreatedBankAccount> createdBankAccounts = createdBankAccountRepository.quantityCreateCreditAccountByEmployee(month, year, employeeId);
			total += createdBankAccounts.size() * 500000;
		}
		salary.setBonusSalary(total);
		salaryRepository.save(salary);
		return true;
	}
	
	@PostMapping("/update/{id}")
	public boolean update(@RequestBody Salary salary, @PathVariable("id") int employeeId) {
		List<Salary> salaries = salaryService.findSalariesByEmployee(employeeId);
		for (Salary salary2 : salaries) {
			if(salary2.getId() != salary.getId() && salary2.getDateSalary().equalsIgnoreCase(salary.getDateSalary())) return false;
		}
		Employee employee = employeeService.findByIdAndStatusTrue(employeeId);
		salary.setEmployee(employee);
		List<Transaction> transactions = transactionService.findFirstTransactionInMonth(employeeId, salary.getDateSalary());
		double total = 0;
		for (Transaction transaction : transactions) {
			total += transaction.getMoney() * 0.02;
		}
		if(employee.getPosition().equalsIgnoreCase("MANAGER")) {
			transactions = transactionService.findFirstTransactionToManager(employeeId, salary.getDateSalary());
			for (Transaction transaction : transactions) {
				total += transaction.getMoney() * 0.02;
			}
			String datas[] = salary.getDateSalary().split("\\/");
			int month = Integer.parseInt(datas[0]);
			int year = Integer.parseInt(datas[1]);
			List<CreatedBankAccount> createdBankAccounts = createdBankAccountRepository.quantityCreateCreditAccountByEmployee(month, year, employeeId);
			total += createdBankAccounts.size() * 500000;
		}
		salary.setBonusSalary(total);
		salaryRepository.save(salary);
		return true;
	}
	
	@PostMapping("/filter")
	public List<Salary> filter(@RequestBody Map<String, Object> map){
		return salaryService.filter(map);
	}
	
}
