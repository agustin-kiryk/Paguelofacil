package com.paguelofacil.controller;

import com.paguelofacil.service.Impl.TransactionService;
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

  @GetMapping("https://sandbox.paguelofacil.com/PFManagementServices/api/v1/MerchantTransactions")
  public ResponseEntity<String> getTransactions(){
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
    return response;
  }

}
