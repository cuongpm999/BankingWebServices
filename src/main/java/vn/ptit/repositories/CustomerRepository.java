package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
}
