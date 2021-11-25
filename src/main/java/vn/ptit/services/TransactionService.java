package vn.ptit.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.ptit.entities.DepositAccount;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;

@Service
public class TransactionService {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired DepositAccountRepository depositAccountRepository;

	public List<Transaction> findFirstTransactionOfDepositAccountInMonth(int employeeId) {
		List<Transaction> transactions = transactionRepository.findAll();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		int month = Integer.parseInt(sdf.format(now));
		String sql = "SELECT Transaction.* FROM Transaction,"
				+ " (SELECT depositAccountId, MIN(dateCreate)"
				+ " AS minDate FROM Transaction"
				+ " WHERE employeeId = " + employeeId 
				+ " AND type = 'DEPOSIT' AND MONTH(dateCreate) = " 
				+ month +  " GROUP BY depositAccountId) AS A WHERE Transaction.dateCreate = A.minDate";
		Query query = entityManager.createNativeQuery(sql, Transaction.class);
		List<Transaction> transactions1 = query.getResultList();
		for (Transaction transaction : transactions) {
			if (transaction.getType().equals("DEPOSIT")
					&& Integer.parseInt(sdf.format(transaction.getDateCreate())) < month) {
				transactions1.removeIf(t -> t.getDepositAccount().getId() == transaction.getDepositAccount().getId());
			}
		}
		return transactions1;
	}
	
	public List<Transaction> findFirstTransactionOfDepositAccount(int employeeId){
		List<Transaction> transactions = transactionRepository.findAll();
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		int month = Integer.parseInt(sdf.format(now));
		String sql = "SELECT Transaction.* FROM Transaction,"
				+ " (SELECT T.depositAccountId, MIN(T.dateCreate) AS minDate"
				+ " FROM Transaction AS T, CreatedBankAccount AS C"
				+ " WHERE C.employeeId = " + employeeId
				+ " AND T.EmployeeId = C.EmployeeId  AND T.type = 'DEPOSIT'"
				+ " AND MONTH(C.dateCreate) = " + month
				+ " GROUP BY T.depositAccountId) AS A"
				+ " WHERE Transaction.dateCreate = A.minDate;";
		Query query = entityManager.createNativeQuery(sql, Transaction.class);
		List<Transaction> transactions1 = query.getResultList();
		for (Transaction transaction : transactions) {
			if (transaction.getType().equals("DEPOSIT")
					&& Integer.parseInt(sdf.format(transaction.getDateCreate())) < month) {
				transactions1.removeIf(t -> t.getDepositAccount().getId() == transaction.getDepositAccount().getId());
			}
		}
		return transactions1;
	}
}
