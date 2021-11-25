package vn.ptit.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.Employee;
import vn.ptit.entities.Salary;
import vn.ptit.repositories.SalaryRepository;
import vn.ptit.services.SalaryService;

@RestController
@RequestMapping("/rest/api/salary")
public class SalaryController {
	@Autowired SalaryRepository salaryRepository;
	@Autowired SalaryService salaryService;
	
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
	public Salary insert(@RequestBody Salary salary, @PathVariable("id") int employeeId) {
		Employee employee = new Employee();
		employee.setId(employeeId);
		salary.setEmployee(employee);
		return salaryRepository.save(salary);
	}
}
