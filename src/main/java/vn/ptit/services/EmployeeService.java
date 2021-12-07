package vn.ptit.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.Customer;
import vn.ptit.entities.Employee;

@Service
public class EmployeeService {
	@PersistenceContext
	private EntityManager entityManager;
	
	private int LIMIT = 20;

	public Employee loadEmployeeByUsername(String username) {
		try {
			String jpql = "select e from Employee e where e.account.username='" + username +"' and e.status=true";
			Query query = entityManager.createQuery(jpql, Employee.class);
			List<Employee> list= query.getResultList();
			if(list.isEmpty()) return null;
			return (Employee) list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Employee> findAllByStatusTrue(Map<String, Object> map){
		int page = 1;
		String jpql = "select e from Employee e where e.status=true";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("page")) {
				page = (int) entry.getValue();
			} else if (entry.getKey().equalsIgnoreCase("keyEmployee")) {
				jpql += " and (e.fullName like '%" + entry.getValue().toString() + "%' or e.address like '%"
						+ entry.getValue().toString() + "%')";
			} else if (entry.getKey().equalsIgnoreCase("fromDate")) {
				jpql += " and e.dateOfBirth >= '" + entry.getValue().toString() + "'";
			} else if (entry.getKey().equalsIgnoreCase("toDate")) {
				jpql += " and e.dateOfBirth <= '" + entry.getValue().toString() + "'";
			}
		}
		Query query = entityManager.createQuery(jpql, Employee.class);
		query.setFirstResult((page - 1) * LIMIT);
		query.setMaxResults(LIMIT);
		
		List<Employee> employees = query.getResultList();
		if (map.containsKey("sort")) {
			if (map.get("sort").toString().equalsIgnoreCase("a-z")) {
				Collections.sort(employees, new Comparator<Employee>() {
					@Override
					public int compare(Employee o1, Employee o2) {
						String name1 = o1.getFullName().substring(o1.getFullName().lastIndexOf(" ") + 1)
								+ o1.getFullName();
						String name2 = o2.getFullName().substring(o2.getFullName().lastIndexOf(" ") + 1)
								+ o2.getFullName();
						return name1.compareToIgnoreCase(name2);
					}
				});
			}
			if (map.get("sort").toString().equalsIgnoreCase("z-a")) {
				Collections.sort(employees, new Comparator<Employee>() {
					@Override
					public int compare(Employee o1, Employee o2) {
						String name1 = o1.getFullName().substring(o1.getFullName().lastIndexOf(" ") + 1)
								+ o1.getFullName();
						String name2 = o2.getFullName().substring(o2.getFullName().lastIndexOf(" ") + 1)
								+ o2.getFullName();
						return name2.compareToIgnoreCase(name1);
					}
				});
			}
		}
		
		return employees;
	}
	
	public Employee findByIdAndStatusTrue(int id){
		String jpql = "select e from Employee e where e.status=true and e.id = "+id;
		Query query = entityManager.createQuery(jpql, Employee.class);
		return (Employee) query.getResultList().get(0);
	}
}

