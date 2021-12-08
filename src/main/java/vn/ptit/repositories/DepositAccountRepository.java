package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.DepositAccount;

@Repository
public interface DepositAccountRepository extends JpaRepository<DepositAccount, String>{
	@Query("select sum(u.balance) from DepositAccount u where u.status = true")
	public Integer totalDeposit();
	
	@Query(value = "SELECT COUNT(CreatedBankAccount.Id) FROM CreatedBankAccount,BankAccount,DepositAccount where CreatedBankAccount.BankAccountId = BankAccount.Id and BankAccount.Id = DepositAccount.BankAccountId and BankAccount.status = 1 and CreatedBankAccount.CustomerId = ?1", nativeQuery = true)
    public Integer countDepositAccountByCustomer(int id);
}
