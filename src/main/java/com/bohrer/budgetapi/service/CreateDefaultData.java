package com.bohrer.budgetapi.service;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.AccountRepository;
import com.bohrer.budgetapi.repository.BudgetItemRepository;
import com.bohrer.budgetapi.repository.BudgetRepository;
import com.bohrer.budgetapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateDefaultData {
    /**
     * 
     * This is not proper to set up data but 
     * since this project is for demos only
     * then this is fine
     */
    
    @Autowired
    private UserRepository userRepository;
    @Autowired 
    private AccountRepository accountRepository;
    @Autowired 
    private BudgetRepository budgetRepository;
    @Autowired 
    private BudgetItemRepository budgetItemRepository;


    public void createData() {
        User user = new User();
        user.setPassword("password");
        user.setUsername("user");
        userRepository.save(user);
        Account account = new Account();
        account.setEndDate(new Date(10));
        account.setEndDate(new Date(0));
        account.setUser(user);
        accountRepository.save(account);
        Budget budget = new Budget();
        budget.setMonth(8);
        budget.setYear(2021);
        budget.setUser(user);
        budgetRepository.save(budget);
        BudgetItem item = new BudgetItem();
        item.setAmount(300);
        item.setTitle("Food");
        item.setIncome(false);
        item.setBudget(budget);
        budgetItemRepository.save(item);
        Set<BudgetItem> items = new HashSet<>();
        items.add(item);
        budget.setItems(items);
        budgetRepository.save(budget);
        Set<Budget> budgets = new HashSet<>();
        budgets.add(budget);
        user.setAccount(account);
        user.setBudgets(budgets);
        userRepository.save(user);
        
    }

}
