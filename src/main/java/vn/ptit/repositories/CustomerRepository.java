package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Customer;
import vn.ptit.entities.Employee;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
