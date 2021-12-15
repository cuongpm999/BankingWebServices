package vn.ptit.controllers;

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
import vn.ptit.entities.EmployeeSalary;
import vn.ptit.entities.Salary;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.CreatedBankAccountRepository;
import vn.ptit.repositories.EmployeeRepository;
import vn.ptit.repositories.SalaryRepository;
import vn.ptit.services.EmployeeService;
import vn.ptit.services.SalaryService;
import vn.ptit.services.TransactionService;

@RestController
@RequestMapping("/rest/api/salary")
public class SalaryController {
	@Autowired
	SalaryRepository salaryRepository;
	@Autowired
	SalaryService salaryService;
	@Autowired
	TransactionService transactionService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	CreatedBankAccountRepository createdBankAccountRepository;
	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping("/find-by-id/{id}")
	public Salary findById(@PathVariable("id") int salaryId) {
		Optional<Salary> optSalary = salaryRepository.findById(salaryId);
		if (optSalary.isPresent()) {
			return optSalary.get();
		}
		return null;
	}

	@GetMapping("/find-salary-in-month/{filter}")
	public List<EmployeeSalary> filter_(@PathVariable("filter") String filter) {
		String month = filter.split("\\-")[0];
		String year = filter.split("\\-")[1];
		int page = Integer.parseInt(filter.split("\\-")[2]);
		return salaryService.getSalariesInMonth(month, year, page);
	}

	@PostMapping("/find-salary-detail")
	public Salary findSalaryDetail(@RequestBody List<String> text) {
		return salaryService.findSalaryDetail(text.get(0), text.get(1), text.get(2));
	}

	@GetMapping("/insert/{dateSalary}")
	public boolean insert(@PathVariable("dateSalary") String dateSalary) {
		String date[] = dateSalary.split("\\-");
		dateSalary = date[0] + "/" + date[1];
		List<Salary> salaries = salaryRepository.findByDateSalary(dateSalary);
		if (salaries.size() > 0) {
			for (Salary salary : salaries) {
				salary.setBonusSalary(calcBonusSalary(salary));
				salaryRepository.save(salary);
			}
		} else {
			List<Employee> employees = employeeRepository.getAllByStatus();
			Salary salary;
			for (Employee employee : employees) {
				salary = new Salary();
				salary.setEmployee(employee);
				salary.setDateSalary(dateSalary);
				salary.setBasicSalary(employee.getBasicSalary());
				salary.setBonusSalary(calcBonusSalary(salary));
				salaryRepository.save(salary);
			}
		}
		return true;
	}

	private double calcBonusSalary(Salary salary) {
		List<Transaction> transactions = transactionService.findFirstTransactionInMonth(salary.getEmployee().getId(),
				salary.getDateSalary());
		double total = 0;
		for (Transaction transaction : transactions) {
			total += transaction.getMoney() * 0.02;
		}
		if (salary.getEmployee().getPosition().equalsIgnoreCase("MANAGER")) {
			transactions = transactionService.findFirstTransactionToManager(salary.getEmployee().getId(),
					salary.getDateSalary());
			for (Transaction transaction : transactions) {
				total += transaction.getMoney() * 0.02;
			}
			String datas[] = salary.getDateSalary().split("\\/");
			int month = Integer.parseInt(datas[0]);
			int year = Integer.parseInt(datas[1]);
			List<CreatedBankAccount> createdBankAccounts = createdBankAccountRepository
					.quantityCreateCreditAccountByEmployee(month, year, salary.getEmployee().getId());
			total += createdBankAccounts.size() * 500000;
		}
		return total;
	}

	@PostMapping("/filter")
	public List<Salary> filter(@RequestBody Map<String, Object> map) {
		return salaryService.filter(map);
	}

	@GetMapping("/export-all-salary-in-month/{filter}")
	public List<EmployeeSalary> exportExcel(@PathVariable("filter") String filter) {
		String month = filter.split("\\-")[0];
		String year = filter.split("\\-")[1];
		return salaryService.getAllSalariesInMonth(month, year);
	}

}
