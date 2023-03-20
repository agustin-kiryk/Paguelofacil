package com.paguelofacil.service;

import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.repository.Specif.TransactionSpecification;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ITransactionService {


    ResponseEntity<String> getTransactionsAndSave();

    List<TransactionEntity> getTransactionsByFilters(TransactionSpecification specification, Integer limit, Integer offset, String sort);
}
