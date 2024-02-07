package com.bank_app.banking_app.service;

import java.util.List;

import com.bank_app.banking_app.DTO.AccountDto;

public interface AccountService {
    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    AccountDto deposit(Long id, double amt);
    AccountDto withDraw(Long id,double amt);
   List<AccountDto>getAllAccount();
   void deleteAccount(Long id);
}
