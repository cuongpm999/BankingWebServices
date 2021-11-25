package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.DepositAccount;

@Service
public class DepositAccountService {
	@PersistenceContext
	private EntityManager entityManager;

	public List<DepositAccount> findByCustomerId(int id) {
		String jpql = "select a from DepositAccount a, CreatedBankAccount b where a.id = b.bankAccount.id and a.status = true and b.customer.id = "
				+ id;
		Query query = entityManager.createQuery(jpql, DepositAccount.class);
		return query.getResultList();
	}

	public DepositAccount findByIdAndStatusTrue(String id) {
		String jpql = "select a from DepositAccount a where a.status = true and a.id = '" + id + "'";
		Query query = entityManager.createQuery(jpql, DepositAccount.class);
		return (DepositAccount) query.getResultList().get(0);
	}
	
	public List<DepositAccount> findAllAndStatusTrue() {
		String jpql = "select a from DepositAccount a where a.status = true order by a.balance desc";
		Query query = entityManager.createQuery(jpql, DepositAccount.class);
		return query.getResultList();
	}

}
