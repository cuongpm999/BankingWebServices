package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer>{
	@Query("SELECT s FROM Salary s WHERE s.dateSalary = ?1")
	public List<Salary> findByDateSalary(String dateSalary);
}
