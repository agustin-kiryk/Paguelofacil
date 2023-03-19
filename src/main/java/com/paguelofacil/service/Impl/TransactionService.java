package com.paguelofacil.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.entity.TransactionJson;
import com.paguelofacil.mapper.TransactionMapper;
import com.paguelofacil.repository.ITransactionRepository;
import com.paguelofacil.service.ITransactionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService implements ITransactionService {

   @Autowired
   private RestTemplate restTemplate;

   @Autowired
   private TransactionMapper transactionMapper;

   @Autowired
   private ITransactionRepository transactionRepository;

   @Value("${paguelofacil.token}")
   private String paguelofacilToken;

   @Value("${paguelofacil.transactions.url}")
   private String paguelofacilTransactionsUrl;

   @Override
   public ResponseEntity<String> getTransactions2() {
      HttpHeaders headers = new HttpHeaders();
      headers.set("Authorization", paguelofacilToken);

      HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);

      ResponseEntity<String> response = restTemplate.exchange(
          paguelofacilTransactionsUrl,
          HttpMethod.GET,
          httpEntity,
          String.class);

      String jsonResponse = response.getBody();

      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      JsonNode root = null;
      try {
         root = objectMapper.readTree(jsonResponse);
      } catch (JsonProcessingException e) {
         e.printStackTrace();
      }

      assert root != null;
      List<TransactionJson> transactionsJson = objectMapper.convertValue(root.path("data"), new TypeReference<List<TransactionJson>>(){});

      List<TransactionEntity> transactions = transactionsJson.stream()
          .map(transactionMapper::toTransactionEntity)
          .collect(Collectors.toList());

      List<TransactionEntity> newTransactions = new ArrayList<>();
      for (TransactionEntity transaction : transactions) {
         Optional<TransactionEntity> optionalTransaction = transactionRepository.findByIdTransaction(transaction.getIdTransaction());
         if (!optionalTransaction.isPresent()) {
            newTransactions.add(transaction);
         }
      }

      transactionRepository.saveAll(newTransactions);

      return response;
   }
   }

