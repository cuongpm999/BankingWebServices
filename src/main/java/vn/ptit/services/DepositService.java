package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Transaction;

@Service
public class DepositService {
	@PersistenceContext
	private EntityManager entityManager;

	public List<Transaction> findByDepositAccount(String id) {
		String jpql = "select a from Transaction a where a.type = 'DEPOSIT' and a.depositAccount.id =" + id;
		Query query = entityManager.createQuery(jpql, Transaction.class);
		return query.getResultList();
	}
}
