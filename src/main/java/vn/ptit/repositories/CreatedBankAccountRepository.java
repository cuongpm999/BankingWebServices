package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.CreatedBankAccount;

@Repository
public interface CreatedBankAccountRepository extends JpaRepository<CreatedBankAccount, Integer>{

}
