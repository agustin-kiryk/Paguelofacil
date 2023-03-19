package com.paguelofacil.mapper;

import com.paguelofacil.dto.TransactionDto;
import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.entity.TransactionJson;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public TransactionDto toTransactionDto(TransactionEntity transactionEntity) {
    TransactionDto transactionDto = new TransactionDto();
    transactionDto.setIdTransaction(transactionEntity.getIdTransaction());
    transactionDto.setEmail(transactionEntity.getEmail());
    transactionDto.setPhone(transactionEntity.getPhone());
    return transactionDto;
  }

  public List<TransactionDto> toTransactionDtos(List<TransactionEntity> transactionEntities) {
    return transactionEntities.stream()
        .map(this::toTransactionDto)
        .collect(Collectors.toList());
  }

  public TransactionEntity toTransactionEntity(TransactionJson transactionJson) {
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setIdTransaction(Long.valueOf(transactionJson.getIdTransaction()));
    transactionEntity.setEmail(transactionJson.getEmail());
    transactionEntity.setPhone(transactionJson.getPhone());
    return transactionEntity;
  }
}

