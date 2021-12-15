package vn.ptit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.EmployeeSalary;
import vn.ptit.entities.Salary;

@Service
public class SalaryService {
	@PersistenceContext
	EntityManager entityManager;
	private int LIMIT = 20;
		
	public Salary findSalaryDetail(String empid, String month, String year) {
		String jpql = "SELECT s FROM Salary s WHERE s.employee.id = '" +empid + "'";
		jpql += " AND substring(s.dateSalary,1,2) = '" + month + "'";
		jpql += "AND substring(s.dateSalary,4,4) = '" + year + "'";
		Query query = entityManager.createQuery(jpql, Salary.class);
		return (Salary) query.getResultList().get(0);
	}
	
	public List<EmployeeSalary> getSalariesInMonth(String month, String year, int page) {
		String sql = "SELECT e.*, (s.basicSalary + s.bonusSalary) AS totalSalary " + "FROM Employee AS e, Salary AS s "
				+ "WHERE e.id = s.employeeId " + "AND e.status = 1 " + "AND substring(s.dateSalary,1,2) = '" + month
				+ "'" + "AND substring(s.dateSalary,4,4) = '" + year + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		List<Object[]> records = query.getResultList();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalary> employeeSalaries = new ArrayList<>();
		for (int i = 0; i < records.size(); i++) {
			EmployeeSalary employeeSalary = new EmployeeSalary();
			employeeSalary.setId(Integer.parseInt(records.get(i)[0].toString()));
			employeeSalary.setIdCard(records.get(i)[1].toString());
			employeeSalary.setFullName(records.get(i)[2].toString());
			try {
				employeeSalary.setDateOfBirth(simpleDateFormat.parse(records.get(i)[3].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			employeeSalary.setAddress(records.get(i)[4].toString());
			employeeSalary.setLevel(Integer.parseInt(records.get(i)[5].toString()));
			employeeSalary.setSeniority(Integer.parseInt(records.get(i)[6].toString()));
			employeeSalary.setPosition(records.get(i)[7].toString());
			employeeSalary.setBasicSalary(Double.parseDouble(records.get(i)[8].toString()));
			employeeSalary.setTotalSalary(Double.parseDouble(records.get(i)[10].toString()));
			employeeSalaries.add(employeeSalary);
		}
		return employeeSalaries;
	}

	public List<Salary> filter(Map<String, Object> map) {
		String jpql = "SELECT s FROM Salary s WHERE 1=1";
		int page = 1;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("empId")) {
				jpql += " and s.employee.id = " + entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("page")) {
				page = (int) entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("fromDate")) {
				String datas[] = entry.getValue().toString().split("\\/");
				int month = Integer.parseInt(datas[0]);
				int year = Integer.parseInt(datas[1]);
				jpql += " and SUBSTRING(s.dateSalary, 1, 2) >= '" + month + "'";
				jpql += " and SUBSTRING(s.dateSalary, 4, 4) >= '" + year + "'";
			} else if (entry.getKey().equalsIgnoreCase("toDate")) {
				String datas[] = entry.getValue().toString().split("\\/");
				int month = Integer.parseInt(datas[0]);
				int year = Integer.parseInt(datas[1]);
				jpql += " and SUBSTRING(s.dateSalary, 1, 2) <= '" + month + "'";
				jpql += " and SUBSTRING(s.dateSalary, 4, 4) <= '" + year + "'";
			}
		}
		Query query = entityManager.createQuery(jpql, Salary.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		List<Salary> salaries = query.getResultList();
		if (map.containsKey("sort")) {
			if (map.get("sort").toString().equalsIgnoreCase("asc")) {
				Collections.sort(salaries, new Comparator<Salary>() {
					@Override
					public int compare(Salary o1, Salary o2) {
						return (int) ((o1.getBasicSalary() + o1.getBonusSalary())
								- (o2.getBasicSalary() + o2.getBonusSalary()));
					}
				});
			} else if (map.get("sort").toString().equalsIgnoreCase("desc")) {
				Collections.sort(salaries, new Comparator<Salary>() {
					@Override
					public int compare(Salary o1, Salary o2) {
						return (int) ((o2.getBasicSalary() + o2.getBonusSalary())
								- (o1.getBasicSalary() + o1.getBonusSalary()));
					}
				});
			}
		}
		return salaries;
	}
	
	public List<EmployeeSalary> getAllSalariesInMonth(String month, String year) {
		String sql = "SELECT e.*, (s.basicSalary + s.bonusSalary) AS totalSalary " + "FROM Employee AS e, Salary AS s "
				+ "WHERE e.id = s.employeeId " + "AND e.status = 1 " + "AND substring(s.dateSalary,1,2) = '" + month
				+ "'" + "AND substring(s.dateSalary,4,4) = '" + year + "'";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> records = query.getResultList();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<EmployeeSalary> employeeSalaries = new ArrayList<>();
		for (int i = 0; i < records.size(); i++) {
			EmployeeSalary employeeSalary = new EmployeeSalary();
			employeeSalary.setId(Integer.parseInt(records.get(i)[0].toString()));
			employeeSalary.setIdCard(records.get(i)[1].toString());
			employeeSalary.setFullName(records.get(i)[2].toString());
			try {
				employeeSalary.setDateOfBirth(simpleDateFormat.parse(records.get(i)[3].toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			employeeSalary.setAddress(records.get(i)[4].toString());
			employeeSalary.setLevel(Integer.parseInt(records.get(i)[5].toString()));
			employeeSalary.setSeniority(Integer.parseInt(records.get(i)[6].toString()));
			employeeSalary.setPosition(records.get(i)[7].toString());
			employeeSalary.setBasicSalary(Double.parseDouble(records.get(i)[8].toString()));
			employeeSalary.setTotalSalary(Double.parseDouble(records.get(i)[10].toString()));
			employeeSalaries.add(employeeSalary);
		}
		return employeeSalaries;
	}

}
