package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.CustomerCreditStat;
import vn.ptit.entities.CustomerTransactionStat;
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
	
	public List<CustomerTransactionStat> findAllWithTransactionInMonth(String type, String dateStart, String dateEnd){
		String sql = "SELECT Customer.*,A.TongTienGiaoDich FROM Customer, (SELECT CustomerId,SUM(Money) AS 'TongTienGiaoDich' FROM Transaction WHERE Type='"+type+"' AND DateCreate >= '"+dateStart+"' AND DateCreate <= '"+dateEnd+"' GROUP BY CustomerId) AS A WHERE Customer.Id = A.CustomerId";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> records = query.getResultList();
		List<CustomerTransactionStat> customerTransactionStats = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=0;i<records.size();i++) {
			CustomerTransactionStat customerTransactionStat = new CustomerTransactionStat();
			customerTransactionStat.setId(Integer.parseInt(records.get(i)[0].toString()));
			customerTransactionStat.setFullName(records.get(i)[1].toString());
			customerTransactionStat.setIdCard(records.get(i)[2].toString());
			System.out.println(records.get(i)[3].toString());
			try {
				customerTransactionStat.setDateOfBirth(simpleDateFormat.parse(records.get(i)[3].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			customerTransactionStat.setAddress(records.get(i)[4].toString());
			customerTransactionStat.setTongTienGiaoDich(Double.parseDouble(records.get(i)[6].toString()));
			customerTransactionStats.add(customerTransactionStat);
		}
		
		return customerTransactionStats;
	}

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
