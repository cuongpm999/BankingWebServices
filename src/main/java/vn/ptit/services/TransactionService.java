package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import vn.ptit.entities.Employee;
import vn.ptit.entities.Transaction;
import vn.ptit.repositories.DepositAccountRepository;
import vn.ptit.repositories.TransactionRepository;

@Service
public class TransactionService {
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	DepositAccountRepository depositAccountRepository;

	public List<CustomerTransactionStat> findAllWithTransactionInMonth(String type, String dateStart, String dateEnd) {
		String sql = "SELECT Customer.*,A.TongTienGiaoDich FROM Customer, (SELECT CustomerId,SUM(Money) AS 'TongTienGiaoDich' FROM Transaction WHERE Type='"
				+ type + "' AND DateCreate >= '" + dateStart + "' AND DateCreate <= '" + dateEnd
				+ "' GROUP BY CustomerId) AS A WHERE Customer.Id = A.CustomerId";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> records = query.getResultList();
		List<CustomerTransactionStat> customerTransactionStats = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < records.size(); i++) {
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
			customerTransactionStat.setEmail(records.get(i)[6].toString());
			customerTransactionStat.setTongTienGiaoDich(Double.parseDouble(records.get(i)[7].toString()));
			customerTransactionStats.add(customerTransactionStat);
		}
		
		Collections.sort(customerTransactionStats, new Comparator<CustomerTransactionStat>() {

			@Override
			public int compare(CustomerTransactionStat o1, CustomerTransactionStat o2) {

				return Double.compare(o2.getTongTienGiaoDich(), o1.getTongTienGiaoDich());
			}
		});

		return customerTransactionStats;
	}

	public List<Transaction> findFirstTransactionInMonth(int employeeId, String dateSalary) {
		List<Transaction> transactions = findByLowerMonth(dateSalary);
		String datas[] = dateSalary.split("\\/");
		int month = Integer.parseInt(datas[0]);
		int year = Integer.parseInt(datas[1]);
		String sql = "SELECT Transaction.* FROM Transaction," + " (SELECT depositAccountId, MIN(dateCreate)"
				+ " AS minDate FROM Transaction" + " WHERE employeeId = " + employeeId
				+ " AND type = 'DEPOSIT' AND MONTH(dateCreate) = " + month + " AND YEAR(dateCreate) = " + year
				+ " GROUP BY depositAccountId) AS A WHERE Transaction.dateCreate = A.minDate";
		Query query = entityManager.createNativeQuery(sql, Transaction.class);
		List<Transaction> transactions1 = query.getResultList();
		for (Transaction transaction : transactions) {
			transactions1.removeIf(t -> t.getDepositAccount().getId() == transaction.getDepositAccount().getId());
		}
		return transactions1;
	}

	public List<Transaction> findFirstTransactionToManager(int employeeId, String dateSalary) {
		List<Transaction> transactions = findByLowerMonth(dateSalary);
		String datas[] = dateSalary.split("\\/");
		int month = Integer.parseInt(datas[0]);
		int year = Integer.parseInt(datas[1]);
		String sql = "SELECT Transaction.* FROM Transaction,"
				+ " (SELECT T.depositAccountId, MIN(T.dateCreate) AS minDate"
				+ " FROM Transaction AS T, CreatedBankAccount AS C" + " WHERE C.employeeId = " + employeeId
				+ " AND T.EmployeeId = C.EmployeeId  AND T.type = 'DEPOSIT'" + " AND MONTH(T.dateCreate) = " + month
				+ " AND YEAR(T.dateCreate) = " + year + " GROUP BY T.depositAccountId) AS A"
				+ " WHERE Transaction.DateCreate = A.minDate;";
		Query query = entityManager.createNativeQuery(sql, Transaction.class);
		List<Transaction> transactions1 = query.getResultList();
		for (Transaction transaction : transactions) {
			transactions1.removeIf(t -> t.getDepositAccount().getId() == transaction.getDepositAccount().getId());
		}
		return transactions1;
	}

	public List<Transaction> findByLowerMonth(String dateSalary) {
		String datas[] = dateSalary.split("\\/");
		int month = Integer.parseInt(datas[0]);
		int year = Integer.parseInt(datas[1]);
		String jpql = "select e from Transaction e where e.type='DEPOSIT' and MONTH(e.dateCreate) < " + month
				+ " AND YEAR(e.dateCreate) = " + year;
		Query query = entityManager.createQuery(jpql, Transaction.class);
		return query.getResultList();
	}
}
