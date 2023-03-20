package com.paguelofacil.repository;

import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.repository.Specif.TransactionSpecification;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Long>,
    JpaSpecificationExecutor<TransactionEntity> {

  Optional<TransactionEntity> findByIdTransaction(Long valueOf);
}
