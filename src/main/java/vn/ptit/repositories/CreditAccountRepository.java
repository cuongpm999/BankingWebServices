package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.CreditAccount;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, String>{
	@Query(value = "SELECT COUNT(CreatedBankAccount.Id) FROM CreatedBankAccount,BankAccount,CreditAccount where CreatedBankAccount.BankAccountId = BankAccount.Id and BankAccount.Id = CreditAccount.BankAccountId and BankAccount.status = 1 and CreatedBankAccount.CustomerId = ?1", nativeQuery = true)
    public Integer countCreditAccountByCustomer(int id);
}
