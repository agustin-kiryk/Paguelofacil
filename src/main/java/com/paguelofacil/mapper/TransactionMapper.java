package com.paguelofacil.mapper;

import com.paguelofacil.dto.TransactionDto;
import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.entity.TransactionJson;
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
    transactionDto.setAmount(transactionEntity.getAmount());
    transactionDto.setMerchantName(transactionEntity.getMerchantName());
    transactionDto.setBinBank(transactionEntity.getBinBank());
    transactionDto.setTxConcept(transactionEntity.getTxConcept());
    transactionDto.setCodAuth(transactionEntity.getCodAuth());
    transactionDto.setDateTms(transactionEntity.getDateTms());
    transactionDto.setCardType(transactionEntity.getCardType());
    transactionDto.setTxDescription(transactionEntity.getTxDescription());
    return transactionDto;
  }

  public List<TransactionDto> toTransactionDtos(List<TransactionEntity> transactionEntities,
      String fields) {
    return transactionEntities.stream()
        .map(this::toTransactionDto)
        .collect(Collectors.toList());
  }

  public TransactionEntity toTransactionEntity(TransactionJson transactionJson) {
    TransactionEntity transactionEntity = new TransactionEntity();
    transactionEntity.setIdTransaction(Long.valueOf(transactionJson.getIdTransaction()));
    transactionEntity.setEmail(transactionJson.getEmail());
    transactionEntity.setPhone(transactionJson.getPhone());
    transactionEntity.setAmount(transactionJson.getAmount());
    transactionEntity.setMerchantName(transactionJson.getMerchantName());
    transactionEntity.setBinBank(transactionJson.getBinBank());
    transactionEntity.setTxConcept(transactionJson.getTxConcept());
    transactionEntity.setCodAuth(transactionJson.getCodAuth());
    transactionEntity.setDateTms(transactionJson.getDateTms());
    transactionEntity.setCardType(transactionJson.getCardType());
    transactionEntity.setTxDescription(transactionJson.getTxDescription());
    return transactionEntity;
  }
}


