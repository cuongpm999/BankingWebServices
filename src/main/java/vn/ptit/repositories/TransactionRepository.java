package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer>{

}
