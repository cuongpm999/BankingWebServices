package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.BankAccount;

@Repository
public interface BankAccounnRepository extends JpaRepository<BankAccount, String>{
	@Query("select count(u) from BankAccount u where u.status = true")
	public Integer countBankAccount();

}
