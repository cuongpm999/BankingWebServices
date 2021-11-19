package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.CreditAccount;

@Repository
public interface CreditAccountRepository extends JpaRepository<CreditAccount, String>{

}
