package com.bank_app.banking_app.service.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bank_app.banking_app.DTO.AccountDto;
import com.bank_app.banking_app.entity.Account;
import com.bank_app.banking_app.mapper.AccountMapper;
import com.bank_app.banking_app.repsitory.AccountRepository;
import com.bank_app.banking_app.service.AccountService;

import jakarta.persistence.EntityNotFoundException;
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    


    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



   
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account= AccountMapper.mapToAccount(accountDto);
        Account  accountSaved=accountRepository.save(account);

        return  AccountMapper.mapToAccountDto(accountSaved);
    }



   
    @Override
    public AccountDto getAccountById(Long id) {
      Account account= accountRepository
      .findById(id)
      .orElseThrow(()-> new  EntityNotFoundException("Account not found please recheck the account id "));
      return AccountMapper.mapToAccountDto(account);
    }




    @Override
    public AccountDto deposit(Long id, double amt) {
        Account account=accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account is missing  please recheck the account id"));
         double total=account.getBalance()+amt;
         account.setBalance(total);
        Account saveAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
    }




    @Override
    public AccountDto withDraw(Long id, double amt) {
       Account account= accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account is missing  please recheck the account id"));
       try {
        if(account.getBalance()>= amt)
       {
        double total=account.getBalance()-amt;
        account.setBalance(total);
        Account saveAccount= accountRepository.save(account);
        return AccountMapper.mapToAccountDto(saveAccount);
       }
       else
       {
        throw new RuntimeException("Insufficent Balance");
       }
       } catch (Exception e) {
        throw new RuntimeException("Error  processing withdrawal",e);
       }
    }




    @Override
    public List<AccountDto> getAllAccount() {
        List<Account>accounts=accountRepository.findAll();
        return accounts.stream()
        .map((account)-> AccountMapper.mapToAccountDto(account))
        .collect(Collectors.toList());

    }




    @Override
    public void deleteAccount(Long id) {
       Account account=accountRepository.findById(id).orElseThrow(()-> new RuntimeException("Account is missing  please recheck the account id"));
       accountRepository.deleteById(id);
       return ;
    }
    
}