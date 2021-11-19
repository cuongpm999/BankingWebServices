package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer>{

}
