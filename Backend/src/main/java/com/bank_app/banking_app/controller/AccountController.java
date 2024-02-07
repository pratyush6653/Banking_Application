package com.bank_app.banking_app.controller;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_app.banking_app.DTO.AccountDto;
import com.bank_app.banking_app.service.AccountService;

@RestController
@RequestMapping("/home")
public class AccountController {
    private  AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    // add account rest API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }

    // get account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountbyId(@PathVariable Long id)
    {
    AccountDto accountDto=accountService.getAccountById(id);
    return ResponseEntity.ok(accountDto);
    }


    // Deposite Rest API 
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto>deposit(@PathVariable Long id, @RequestBody Map<String,Double>data)
    {
        Double amount=data.get("amount");
         AccountDto accountDto= accountService.deposit(id,amount);
        return ResponseEntity.ok(accountDto);
    }


    // Withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto>withdraw( @PathVariable Long id, @RequestBody Map<String,Double>data)
    {
        Double amount=data.get("amount");
        AccountDto accountDto= accountService.withDraw(id,amount);
        return ResponseEntity.ok(accountDto);
    }

    // get all Accounts REST API
    @GetMapping("/allaccounts")
    public ResponseEntity<List<AccountDto>>getAllAccounts()
    {
        List<AccountDto>accountDtos= accountService.getAllAccount();
        return ResponseEntity.ok(accountDtos);
    }

    // Delete account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount( @PathVariable Long id)
    {
      accountService.deleteAccount(id);
       return ResponseEntity.ok("Deleted !");
    }
}
