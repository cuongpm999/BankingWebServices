package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.CreatedBankAccount;

@Repository
public interface CreatedBankAccountRepository extends JpaRepository<CreatedBankAccount, Integer>{
	@Query("SELECT u FROM CreatedBankAccount u, CreditAccount c WHERE u.bankAccount.id = c.id AND MONTH(u.dateCreate) = ?1 AND YEAR(u.dateCreate) = ?2 AND u.employee.id = ?3")
	public List<CreatedBankAccount> quantityCreateCreditAccountByEmployee(int month,int year, int employeeId);
}
