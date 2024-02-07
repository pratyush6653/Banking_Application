package com.bank_app.banking_app.repsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank_app.banking_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
    
}
