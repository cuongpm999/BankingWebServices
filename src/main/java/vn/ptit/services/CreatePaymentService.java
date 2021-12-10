package vn.ptit.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Transaction;

@Service
public class CreatePaymentService {
	@PersistenceContext
	private EntityManager entityManager;

	private int LIMIT = 20;

	public List<Transaction> findById(String id, Map<String, Object> map) {
		int page = 1;
		String jpql = "select e from Transaction e where type='PAYMENT' and e.depositAccount.id='" + id + "'";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("page")) {
				page = (int) entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("fromDate")) {
				jpql += " and e.dateCreate >= '" + entry.getValue().toString() + "'";
			} else if (entry.getKey().equalsIgnoreCase("toDate")) {
				jpql += " and e.dateCreate <= '" + entry.getValue().toString() + "'";
			}
		}
		Query query = entityManager.createQuery(jpql, Transaction.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		List<Transaction> transactions = query.getResultList();
		if (map.containsKey("sort")) {
			if (map.get("sort").toString().equalsIgnoreCase("Tăng dần")) {
				Collections.sort(transactions, new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						return Double.compare(o1.getMoney(), o2.getMoney());
					}
				});
			}
			if (map.get("sort").toString().equalsIgnoreCase("Giảm dần")) {
				Collections.sort(transactions, new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						return Double.compare(o2.getMoney(), o1.getMoney());
					}
				});
			}
		}
		return transactions;
	}

	public List<Transaction> findTransactionByCreditAccount(String id, Map<String, Object> map) {
		int page = 1;
		String jpql = "select e from Transaction e where type='PAYMENT' and e.creditAccount.id='" + id
				+ "' and e.depositAccount.id is null";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("page")) {
				page = (int) entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("fromDate")) {
				jpql += " and e.dateCreate >= '" + entry.getValue().toString() + "'";
			} else if (entry.getKey().equalsIgnoreCase("toDate")) {
				jpql += " and e.dateCreate <= '" + entry.getValue().toString() + "'";
			}
		}
		Query query = entityManager.createQuery(jpql, Transaction.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		List<Transaction> transactions = query.getResultList();
		if (map.containsKey("sort")) {
			if (map.get("sort").toString().equalsIgnoreCase("Tăng dần")) {
				Collections.sort(transactions, new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						return Double.compare(o1.getMoney(), o2.getMoney());
					}
				});
			}
			if (map.get("sort").toString().equalsIgnoreCase("Giảm dần")) {
				Collections.sort(transactions, new Comparator<Transaction>() {
					@Override
					public int compare(Transaction o1, Transaction o2) {
						return Double.compare(o2.getMoney(), o1.getMoney());
					}
				});
			}
		}
		return transactions;
	}
}
