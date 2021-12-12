package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	@Query("SELECT e FROM Employee e WHERE e.status = 1")
	public List<Employee> getAllByStatus();
}
