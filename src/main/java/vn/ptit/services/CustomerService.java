package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Customer;
import vn.ptit.entities.Employee;

@Service
public class CustomerService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> findAllByStatusTrue(){
		String jpql = "select e from Customer e where e.status=true";
		Query query = entityManager.createQuery(jpql, Customer.class);
		return query.getResultList();
	}
	
	public Customer findByIdAndStatusTrue(int id){
		String jpql = "select e from Customer e where e.status=true and e.id = "+id;
		Query query = entityManager.createQuery(jpql, Customer.class);
		return (Customer) query.getResultList().get(0);
	}
	
	public Customer findByAccountId(String id){
		String jpql = "select e from Customer e, CreatedBankAccount c  where e.status=true and c.customer = e.id and c.bankAccount.id = '"+id+"'";
		Query query = entityManager.createQuery(jpql, Customer.class);
		return (Customer) query.getResultList().get(0);
	}

}
