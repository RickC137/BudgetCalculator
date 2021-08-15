package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyAccountService_Impl implements MyAccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account updateAccount(Account account) {
        Account curAccount = accountRepository.getById(account.getAccountId());
        if(account.getUser() != curAccount.getUser()) {
            return null;
        }
        return accountRepository.save(account);
    }

    @Override
    public void removeAccountById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account addAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account getAccount(Long id) {
        return accountRepository.getById(id);
    }
    
}
