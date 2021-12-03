package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Customer;
import vn.ptit.entities.CustomerCreditStat;
import vn.ptit.entities.CustomerDepositStat;
import vn.ptit.entities.Employee;

@Service
public class CustomerService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Customer> findAllByStatusTrue(){
		String jpql = "select e from Customer e where e.status=true";
		Query query = entityManager.createQuery(jpql, Customer.class);
		return query.getResultList();
	}
	
	public Customer findByIdAndStatusTrue(int id){
		String jpql = "select e from Customer e where e.status=true and e.id = "+id;
		Query query = entityManager.createQuery(jpql, Customer.class);
		return (Customer) query.getResultList().get(0);
	}
	
	public Customer findByAccountId(String id){
		String jpql = "select e from Customer e, CreatedBankAccount c  where e.status=true and c.customer = e.id and c.bankAccount.id = '"+id+"'";
		Query query = entityManager.createQuery(jpql, Customer.class);
		return (Customer) query.getResultList().get(0);
	}

	public List<CustomerDepositStat> findAllWithBalanceInDepositMax(){
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
		for(int i=0;i<records.size();i++) {
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
			
		return customerDepositStats;
	}
	
	public List<CustomerCreditStat> findAllWithBalanceInCreditMax(){
		String sql = "SELECT Customer.*,B.credit "
				+ "FROM (SELECT SUM(A.Balance) AS 'credit', A.CustomerId "
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
		for(int i=0;i<records.size();i++) {
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
		
		return customerCreditStats;
	}
	
}
