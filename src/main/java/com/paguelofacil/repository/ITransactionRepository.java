package com.paguelofacil.repository;

import com.paguelofacil.entity.TransactionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity,Long> {

  Optional<TransactionEntity> findByIdTransaction(Long valueOf);
}
