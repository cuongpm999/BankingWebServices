package vn.ptit.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.DepositAccount;

@Repository
public interface DepositAccountRepository extends JpaRepository<DepositAccount, String>{
}
