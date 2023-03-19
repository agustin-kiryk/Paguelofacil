package com.paguelofacil.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class TransactionDto {

    private Long idTransaction;
    private String email;
    private String phone;

}
