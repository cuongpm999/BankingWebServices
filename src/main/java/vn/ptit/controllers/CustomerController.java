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

import vn.ptit.entities.Customer;
import vn.ptit.repositories.CustomerRepository;
import vn.ptit.services.CustomerService;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerController {
	@Autowired CustomerRepository customerRepository;
	@Autowired CustomerService customerService;
	
	@PostMapping(value = "/find-all")
	public List<Customer> findAll(@RequestBody Map<String, Object> map) {		
		return customerService.findAllByStatusTrue(map);
	}
	
	@PostMapping(value = "/insert")
	public Customer insert(@RequestBody Customer customer) {
		return customerRepository.save(customer);
	}
	
	@GetMapping(value = "/find-by-id/{id}")
	public Customer findById(@PathVariable("id") int id) {
		return customerService.findByIdAndStatusTrue(id);
	}
	
	@DeleteMapping(value = "/delete-by-id/{id}")
	public void deleteCustomerById(@PathVariable("id") int id) {
		Customer customer = customerRepository.findById(id).get();
		customer.setStatus(false);
		customerRepository.save(customer);
	}
	
	@GetMapping(value = "/find-by-account/{id}")
	public Customer findByAccountId(@PathVariable("id") String id) {
		return customerService.findByAccountId(id);
	}
}
