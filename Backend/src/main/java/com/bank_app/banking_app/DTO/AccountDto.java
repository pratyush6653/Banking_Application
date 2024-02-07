package com.bank_app.banking_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class AccountDto {
    private Long id;
    private String  accountHolderName;
    private double balance;
}
