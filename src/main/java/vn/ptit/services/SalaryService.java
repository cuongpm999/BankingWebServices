package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Salary;

@Service
public class SalaryService {
	@PersistenceContext EntityManager entityManager;
	
	public List<Salary> findSalariesByEmployee(int employeeId){
		String jpql = "SELECT s FROM Salary s WHERE s.employee.id = " + employeeId;
		Query query = entityManager.createQuery(jpql, Salary.class);
		return query.getResultList();
	}
}
