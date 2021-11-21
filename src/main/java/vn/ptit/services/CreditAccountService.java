package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.CreditAccount;
import vn.ptit.entities.Employee;

@Service
public class CreditAccountService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<CreditAccount> findByCustomerId(int id) {
		String jpql = "select a from CreditAccount a, CreatedBankAccount b where a.id = b.bankAccount.id and a.status = true and b.customer.id = "+id;
		Query query = entityManager.createQuery(jpql, CreditAccount.class);
		return query.getResultList();
	}
	
	public CreditAccount findByIdAndStatusTrue(String id) {
		String jpql = "select a from CreditAccount a where a.status = true and a.id = "+id;
		Query query = entityManager.createQuery(jpql, CreditAccount.class);
		return (CreditAccount) query.getResultList().get(0);
	}
}
