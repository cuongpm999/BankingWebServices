package vn.ptit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ptit.entities.Customer;
import vn.ptit.repositories.CustomerRepository;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {
	@Autowired CustomerRepository customerRepository;
	
	@GetMapping(value = "/find-all")
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}
	
	@PostMapping(value = "/insert")
	public Customer insert(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public Customer findById(@PathVariable("id") int id) {
		return customerRepository.findById(id).get();
	}
	
	@GetMapping(value = "/delete-by-id/{id}")
	public Integer deleteCustomerById(@PathVariable("id") int id) {
		customerRepository.deleteById(id);
		return 1;
	}
}
