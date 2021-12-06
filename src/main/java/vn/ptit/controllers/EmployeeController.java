package vn.ptit.controllers;

import java.util.List;
import java.util.Map;

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
import vn.ptit.services.EmployeeService;

@RestController
@RequestMapping("/rest/api/employee")
public class EmployeeController {
	@Autowired EmployeeRepository employeeRepository;
	@Autowired EmployeeService employeeService;
	
	@PostMapping("/find-all")
	public List<Employee> findAll(@RequestBody Map<String, Object> map){
		return employeeService.findAllByStatusTrue(map);
	}
	
	@PostMapping("/insert")
	public Employee insert(@RequestBody Employee employee){
		employee.addAccount(employee.getAccount());
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/find-by-id/{id}")
	public Employee findById(@PathVariable("id") int id){
		return employeeService.findByIdAndStatusTrue(id);
	}
	
	@PostMapping("/update")
	public Employee update(@RequestBody Employee employee){
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public void deleteById(@PathVariable("id") int id){
		Employee employee = employeeRepository.findById(id).get();
		employee.setStatus(false);
		employeeRepository.save(employee);
		
	}
	
	@GetMapping("/get/{username}")
	public Employee getByUsername(@PathVariable("username") String username) {
		return employeeService.loadEmployeeByUsername(username);
	}

}
