package com.paguelofacil.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paguelofacil.entity.TransactionEntity;
import com.paguelofacil.entity.TransactionJson;
import com.paguelofacil.repository.ITransactionRepository;
import com.paguelofacil.service.Impl.TransactionService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TransactionController {

  @Autowired
  private TransactionService pagueloFacilClient;
  @Autowired
  private ITransactionRepository transactionRepository;

  @GetMapping("Transactions")
  public ResponseEntity<String> getTransactions() throws JsonProcessingException {
    //crea los encabezados HTTP con el token de "Access token API" de la plataforma
    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "brEyQRSzMm2UwQa5v0NsobRa3U8nH5xT|DIRcACprsihBWW6LkEZ4kre9U");//TODO: HACERLO GENERICO, PONER UNA VARIABLE Y AGARARLO DESDE EL APP PROPERTIES
    //crea la entidad HTTP con los encabezados
    HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
    //Realiza la solicitud GET al endpoint protegido
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> response = restTemplate.exchange(
        "https://sandbox.paguelofacil.com/PFManagementServices/api/v1/MerchantTransactions",
        HttpMethod.GET,
        httpEntity,
        String.class);
    String jsonResponse = response.getBody();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    JsonNode root = objectMapper.readTree(jsonResponse);
    List<TransactionEntity> transacciones = new ArrayList<>();
    for (JsonNode transaccionNode : root.path("data")) {
      TransactionJson transaccionJson = objectMapper.treeToValue(transaccionNode, TransactionJson.class);
      Optional<TransactionEntity> optionalTransaccion = transactionRepository.findById(Long.valueOf(transaccionJson.getIdTransaction()));
      if (!optionalTransaccion.isPresent()) {
        // Si no existe, crear una nueva transacción y agregarla a la lista
        TransactionEntity transaccion = new TransactionEntity();
        transaccion.setIdTransaction(Long.valueOf(transaccionJson.getIdTransaction()));
        transaccion.setEmail(transaccionJson.getEmail());
        transaccion.setPhone(transaccionJson.getPhone());
        transacciones.add(transaccion);
      }
      // Si ya existe, no hacer nada
    }
    transactionRepository.saveAll(transacciones);

    return response;
  }

}
