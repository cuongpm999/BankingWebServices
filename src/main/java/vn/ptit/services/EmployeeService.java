package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Employee;

@Service
public class EmployeeService {
	@PersistenceContext
	private EntityManager entityManager;

	public Employee loadEmployeeByUsername(String username) {
		try {
			String jpql = "select e from Employee e where e.account.username='" + username +"' and e.status=true";
			Query query = entityManager.createQuery(jpql, Employee.class);
			List<Employee> list= query.getResultList();
			if(list.isEmpty()) return null;
			return (Employee) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> findAllByStatusTrue(){
		String jpql = "select e from Employee e where e.status=true";
		Query query = entityManager.createQuery(jpql, Employee.class);
		return query.getResultList();
	}
	
	public Employee findByIdAndStatusTrue(int id){
		String jpql = "select e from Employee e where e.status=true and e.id = "+id;
		Query query = entityManager.createQuery(jpql, Employee.class);
		return (Employee) query.getResultList().get(0);
	}
}

