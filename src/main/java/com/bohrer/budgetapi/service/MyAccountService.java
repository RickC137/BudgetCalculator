package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.Account;

public interface MyAccountService {
    Account updateAccount(Account account);
    void removeAccountById(Long id);
    Account addAccount(Account account);
    Account getAccount(Long id);
}
