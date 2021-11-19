package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.ptit.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
