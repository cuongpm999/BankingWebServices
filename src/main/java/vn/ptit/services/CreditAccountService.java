package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.CreditAccount;
import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.Employee;

@Service
public class CreditAccountService {
	@PersistenceContext
	private EntityManager entityManager;

	public List<CreditAccount> findByCustomerId(int id) {
		String jpql = "select a from CreditAccount a, CreatedBankAccount b where a.id = b.bankAccount.id and a.status = true and b.customer.id = "
				+ id;
		Query query = entityManager.createQuery(jpql, CreditAccount.class);
		return query.getResultList();
	}

	public CreditAccount findByIdAndStatusTrue(String id) {
		String jpql = "select a from CreditAccount a where a.status = true and a.id = '" + id + "'";
		Query query = entityManager.createQuery(jpql, CreditAccount.class);
		return (CreditAccount) query.getResultList().get(0);
	}

	public CreditAccount checkAccountId(String id) {
		try {
			String jpql = "select a from CreditAccount a,CreatedBankAccount b,Customer c where a.status = true and a.id = '"
					+ id + "' and b.bankAccount.id = a.id and b.customer.id = c.id and c.status = true";
			Query query = entityManager.createQuery(jpql, CreditAccount.class);
			List<CreditAccount> list = query.getResultList();
			if (list.isEmpty())
				return null;
			return (CreditAccount) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<CreditAccount> findAllAndStatusTrue() {
		String jpql = "select a from CreditAccount a where a.status = true order by a.balance desc";
		Query query = entityManager.createQuery(jpql, CreditAccount.class);
		query.setMaxResults(10);
		return query.getResultList();
	}
}
