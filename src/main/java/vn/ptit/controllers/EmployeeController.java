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

import vn.ptit.entities.Employee;
import vn.ptit.repositories.EmployeeRepository;

@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeController {
	@Autowired EmployeeRepository employeeRepository;
	
	@GetMapping("/find-all")
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/insert")
	public Employee insert(@RequestBody Employee employee){
		employee.addAccount(employee.getAccount());
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/find-by-id/{id}")
	public Employee findById(@PathVariable("id") int id){
		return employeeRepository.findById(id).get();
	}
	
	@PostMapping("/update")
	public Employee update(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public void deleteById(@PathVariable("id") int id){
		employeeRepository.deleteById(id);
	}
}
