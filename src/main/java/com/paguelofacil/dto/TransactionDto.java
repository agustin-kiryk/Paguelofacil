package com.paguelofacil.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class TransactionDto {

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

}
