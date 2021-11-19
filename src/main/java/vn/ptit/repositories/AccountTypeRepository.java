package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.BankAccountType;

@Repository
public interface AccountTypeRepository extends JpaRepository<BankAccountType, Integer>{

}
