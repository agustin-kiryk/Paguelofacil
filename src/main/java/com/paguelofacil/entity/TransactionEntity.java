package com.paguelofacil.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;
  private Long idTransaction;
  private String email;
  private String phone;
  private Float amount;
  private String merchantName;
  private String binBank;
  private String txConcept;
  private String codAuth;
  private String authStatus;
  private String dateTms;
  private String cardType;
  private String txDescription;


  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }

    TransactionEntity that = (TransactionEntity) obj;

    return Objects.equals(idTransaction, that.idTransaction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idTransaction);
  }


}
