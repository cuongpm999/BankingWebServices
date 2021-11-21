package vn.ptit.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import vn.ptit.entities.BankAccountType;

@Service
public class BankAccountTypeService {
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<BankAccountType> findAllByStatusTrue(){
		String jpql = "select e from BankAccountType e where e.status=true";
		Query query = entityManager.createQuery(jpql, BankAccountType.class);
		return query.getResultList();
	}
	
	public BankAccountType findByIdAndStatusTrue(int id){
		String jpql = "select e from BankAccountType e where e.status=true and e.id = "+id;
		Query query = entityManager.createQuery(jpql, BankAccountType.class);
		return (BankAccountType) query.getResultList().get(0);
	}
}
