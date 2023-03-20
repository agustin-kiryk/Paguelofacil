package com.paguelofacil.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionJson {

  @JsonProperty("idTransaction")
  private Integer idTransaction;
  @JsonProperty("email")
  private String email;
  @JsonProperty("phone")
  private String phone;
  @JsonProperty("amount")
  private Float amount;
  @JsonProperty("merchantName")
  private String merchantName;
  @JsonProperty("binBank")
  private String binBank;
  @JsonProperty("txConcept")
  private String txConcept;
  @JsonProperty("codAuth")
  private String codAuth;
  @JsonProperty("authStatus")
  private String authStatus;
  @JsonProperty("dateTms")
  private String dateTms;
  @JsonProperty("cardType")
  private String cardType;
  @JsonProperty("txDescription")
  private String txDescription;




}
