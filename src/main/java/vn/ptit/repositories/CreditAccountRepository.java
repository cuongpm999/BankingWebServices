package vn.ptit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.CreatedBankAccount;
import vn.ptit.entities.CreditAccount;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, String>{
	@Query("SELECT u FROM CreatedBankAccount u, CreditAccount c WHERE u.bankAccount.id = c.id AND MONTH(u.dateCreate) = ?1 AND u.employee.id = ?2")
	public List<CreatedBankAccount> quantityCreateCreditAccountByEmployee(int month, int employeeId);
}
