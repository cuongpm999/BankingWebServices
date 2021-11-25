package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.CustomerCreditStat;
import vn.ptit.entities.CustomerTransactionStat;

@Service
public class TransactionService {
	@PersistenceContext
	private EntityManager entityManager;
	
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

}
