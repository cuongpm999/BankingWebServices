package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Transaction;

@Service
public class CreatePaymentService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Transaction> findById(String id){
		String jpql = "select e from Transaction e where type='PAYMENT' and e.depositAccount.id='"+id+"'";
		Query query = entityManager.createQuery(jpql, Transaction.class);
		return query.getResultList();
	}
}
