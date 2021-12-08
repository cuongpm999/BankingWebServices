package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Customer;
import vn.ptit.entities.CustomerCreditStat;
import vn.ptit.entities.CustomerDepositStat;
import vn.ptit.entities.CustomerTransactionStat;
import vn.ptit.entities.Employee;

@Service
public class CustomerService {
	@PersistenceContext
	private EntityManager entityManager;

	private int LIMIT = 20;

	public List<Customer> findAllByStatusTrue(Map<String, Object> map) {
		int page = 1;
		String jpql = "select e from Customer e where e.status=true";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("page")) {
				page = (int) entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("keyCustomer")) {
				jpql += " and (e.fullName like '%" + entry.getValue().toString() + "%' or e.address like '%"
						+ entry.getValue().toString() + "%')";
			} else if (entry.getKey().equalsIgnoreCase("fromDate")) {
				jpql += " and e.dateOfBirth >= '" + entry.getValue().toString() + "'";
			} else if (entry.getKey().equalsIgnoreCase("toDate")) {
				jpql += " and e.dateOfBirth <= '" + entry.getValue().toString() + "'";
			}
		}

		Query query = entityManager.createQuery(jpql, Customer.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);

		List<Customer> customers = query.getResultList();
		if (map.containsKey("sort")) {
			if (map.get("sort").toString().equalsIgnoreCase("a-z")) {
				Collections.sort(customers, new Comparator<Customer>() {
					@Override
					public int compare(Customer o1, Customer o2) {
						String name1 = o1.getFullName().substring(o1.getFullName().lastIndexOf(" ") + 1)
								+ o1.getFullName();
						String name2 = o2.getFullName().substring(o2.getFullName().lastIndexOf(" ") + 1)
								+ o2.getFullName();
						return name1.compareToIgnoreCase(name2);
					}
				});
			}
			if (map.get("sort").toString().equalsIgnoreCase("z-a")) {
				Collections.sort(customers, new Comparator<Customer>() {
					@Override
					public int compare(Customer o1, Customer o2) {
						String name1 = o1.getFullName().substring(o1.getFullName().lastIndexOf(" ") + 1)
								+ o1.getFullName();
						String name2 = o2.getFullName().substring(o2.getFullName().lastIndexOf(" ") + 1)
								+ o2.getFullName();
						return name2.compareToIgnoreCase(name1);
					}
				});
			}
		}
		return customers;
	}

	public Customer findByIdAndStatusTrue(int id) {
		String jpql = "select e from Customer e where e.status=true and e.id = " + id;
		Query query = entityManager.createQuery(jpql, Customer.class);
		return (Customer) query.getResultList().get(0);
	}

	public Customer findByAccountId(String id) {
		String jpql = "select e from Customer e, CreatedBankAccount c  where e.status=true and c.customer = e.id and c.bankAccount.id = '"
				+ id + "'";
		Query query = entityManager.createQuery(jpql, Customer.class);
		if (query.getResultList().size() > 0) {
			return (Customer) query.getResultList().get(0);
		}
		return null;
	}

	public List<CustomerDepositStat> findAllWithBalanceInDepositMax() {
		String sql = "SELECT Customer.*,B.deposit AS CustomerDepositStat "
				+ "FROM (SELECT SUM(A.Balance) AS 'deposit', A.CustomerId FROM "
				+ "(SELECT CreatedBankAccount.*,BankAccount.Balance "
				+ "FROM CreatedBankAccount,BankAccount,DepositAccount WHERE "
				+ "CreatedBankAccount.BankAccountId = BankAccount.Id "
				+ "AND BankAccount.Id = DepositAccount.BankAccountId "
				+ "AND BankAccount.status = 1) AS A GROUP BY A.CustomerId ORDER BY SUM(A.Balance) DESC) AS B, Customer "
				+ "WHERE Customer.Id = B.CustomerId and Customer.status = 1";
		Query query = entityManager.createNativeQuery(sql);
		query.setMaxResults(10);
		List<Object[]> records = query.getResultList();
		List<CustomerDepositStat> customerDepositStats = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < records.size(); i++) {
			CustomerDepositStat customerDepositStat = new CustomerDepositStat();
			customerDepositStat.setId(Integer.parseInt(records.get(i)[0].toString()));
			customerDepositStat.setFullName(records.get(i)[1].toString());
			customerDepositStat.setIdCard(records.get(i)[2].toString());
			customerDepositStat.setEmail(records.get(i)[6].toString());
			System.out.println(records.get(i)[3].toString());
			try {
				customerDepositStat.setDateOfBirth(simpleDateFormat.parse(records.get(i)[3].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			customerDepositStat.setAddress(records.get(i)[4].toString());
			customerDepositStat.setDeposit(Double.parseDouble(records.get(i)[7].toString()));
			customerDepositStats.add(customerDepositStat);
		}

		Collections.sort(customerDepositStats, new Comparator<CustomerDepositStat>() {

			@Override
			public int compare(CustomerDepositStat o1, CustomerDepositStat o2) {

				return Double.compare(o2.getDeposit(), o1.getDeposit());
			}
		});

		return customerDepositStats;
	}

	public List<CustomerCreditStat> findAllWithBalanceInCreditMax() {
		String sql = "SELECT Customer.*,B.credit " + "FROM (SELECT SUM(A.Balance) AS 'credit', A.CustomerId "
				+ "FROM (SELECT CreatedBankAccount.*,BankAccount.Balance "
				+ "FROM CreatedBankAccount,BankAccount,CreditAccount WHERE "
				+ "CreatedBankAccount.BankAccountId = BankAccount.Id AND "
				+ "BankAccount.Id = CreditAccount.BankAccountId AND "
				+ "BankAccount.status = 1) AS A GROUP BY A.CustomerId ORDER BY SUM(A.Balance) DESC) AS B, Customer "
				+ "WHERE Customer.Id = B.CustomerId and Customer.status = 1 and B.credit > 0";
		Query query = entityManager.createNativeQuery(sql);
		query.setMaxResults(10);
		List<Object[]> records = query.getResultList();
		List<CustomerCreditStat> customerCreditStats = new ArrayList<>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < records.size(); i++) {
			CustomerCreditStat customerCreditStat = new CustomerCreditStat();
			customerCreditStat.setId(Integer.parseInt(records.get(i)[0].toString()));
			customerCreditStat.setFullName(records.get(i)[1].toString());
			customerCreditStat.setIdCard(records.get(i)[2].toString());
			customerCreditStat.setEmail(records.get(i)[6].toString());
			System.out.println(records.get(i)[3].toString());
			try {
				customerCreditStat.setDateOfBirth(simpleDateFormat.parse(records.get(i)[3].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			customerCreditStat.setAddress(records.get(i)[4].toString());
			customerCreditStat.setCredit(Double.parseDouble(records.get(i)[7].toString()));
			customerCreditStats.add(customerCreditStat);
		}

		Collections.sort(customerCreditStats, new Comparator<CustomerCreditStat>() {

			@Override
			public int compare(CustomerCreditStat o1, CustomerCreditStat o2) {

				return Double.compare(o2.getCredit(), o1.getCredit());
			}
		});

		return customerCreditStats;
	}

}
