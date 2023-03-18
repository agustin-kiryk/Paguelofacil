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




}
