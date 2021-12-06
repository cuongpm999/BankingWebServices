package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.CustomerTransactionStat;
import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.DepositAccountWithDateCreate;

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
		query.setMaxResults(10);
		return query.getResultList();
	}
	
	public List<DepositAccountWithDateCreate> findAllWithStatusTrueAndDateCreate() {
		String sql = "SELECT bankaccount.Id, bankaccount.Balance, depositaccount.InterestRate, depositaccount.MinimumBalance, createdbankaccount.DateCreate FROM depositaccount, createdbankaccount, bankaccount WHERE depositaccount.BankAccountId = createdbankaccount.BankAccountId AND depositaccount.BankAccountId = bankaccount.Id AND bankaccount.status = 1";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> records = query.getResultList();
		List<DepositAccountWithDateCreate> depositAccountWithDateCreates = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (int i = 0; i < records.size(); i++) {
			DepositAccountWithDateCreate depositAccountWithDateCreate = new DepositAccountWithDateCreate();
			depositAccountWithDateCreate.setId(records.get(i)[0].toString());
			depositAccountWithDateCreate.setBalance(Double.parseDouble(records.get(i)[1].toString()));
			depositAccountWithDateCreate.setInterestRate(Double.parseDouble(records.get(i)[2].toString()));
			depositAccountWithDateCreate.setMinimumBalance(Double.parseDouble(records.get(i)[3].toString()));
			try {
				depositAccountWithDateCreate.setDateCreate(simpleDateFormat.parse(records.get(i)[4].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			depositAccountWithDateCreates.add(depositAccountWithDateCreate);
		}

		return depositAccountWithDateCreates;
	}

}
