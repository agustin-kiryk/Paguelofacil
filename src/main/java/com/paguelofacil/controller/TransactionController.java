package com.paguelofacil.controller;


import com.paguelofacil.dto.TransactionDto;
import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.mapper.TransactionMapper;
import com.paguelofacil.repository.ITransactionRepository;
import com.paguelofacil.repository.Specif.TransactionSpecification;
import com.paguelofacil.service.ITransactionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransactionController {

  @Autowired
  private ITransactionService transactionService;
  @Autowired
  private ITransactionRepository transactionRepository;

  @Autowired
  private TransactionMapper transactionMapper;
  @Autowired
  private TransactionSpecification transactionSpecification;


  //Guarda las transacciones de la api en la base de datos con los atributos seteados
  @GetMapping("TransactionsGetAndSave")
  public ResponseEntity<String> getTransactionsAndSave() {
    ResponseEntity<String> transactions = transactionService.getTransactionsAndSave();
    return transactions;
  }

  //Filtra y ordena las transacciones de la base de datos segun los parametros presstablecidos en la documentacion
  @GetMapping("transactions")
  public ResponseEntity<List<TransactionDto>> getTransactions(
      @RequestParam(value = "filter", required = false) String filter,
      @RequestParam(value = "conditional", required = false) String conditional,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "offset", required = false) Integer offset,
      @RequestParam(value = "sort", required = false) String sort,
      @RequestParam(value = "fields", required = false) String fields
  ) {
    Specification<TransactionEntity> specification =
        filter != null ? new TransactionSpecification(filter,
            conditional) : null;
    List<TransactionEntity> transactions = transactionService.getTransactionsByFilters(
        (TransactionSpecification) specification, limit, offset, sort);
    List<TransactionDto> transactionDtos = transactionMapper.toTransactionDtos(transactions,
        fields);

    return ResponseEntity.ok(transactionDtos);
  }


}




