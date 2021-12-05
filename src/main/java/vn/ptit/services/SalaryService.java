package vn.ptit.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Salary;

@Service
public class SalaryService {
	@PersistenceContext
	EntityManager entityManager;
	private int LIMIT = 5;

	public List<Salary> findSalariesByEmployee(int employeeId) {
		String jpql = "SELECT s FROM Salary s WHERE s.employee.id = " + employeeId;
		Query query = entityManager.createQuery(jpql, Salary.class);
		return query.getResultList();
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

}
