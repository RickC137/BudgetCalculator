package com.bohrer.budgetapi.controller;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.service.BudgetService;
import com.bohrer.budgetapi.service.ItemService;
import com.bohrer.budgetapi.service.MyAccountService;
import com.bohrer.budgetapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BudgetController {
 
    @Autowired
    private UserService userService;

    @Autowired
    private MyAccountService myAccountService;
 
    @Autowired
    private BudgetService budgetService;
 
    @Autowired
    private ItemService itemService;

    @PostMapping("/create/item")
    public BudgetItem createItem(@RequestBody BudgetItem item) {
        return itemService.addItem(item);
    }

    @PostMapping("/create/account")
    public Account createAccount(@RequestBody Account account) {
        return myAccountService.addAccount(account);
    }

    @PostMapping("/create/budget")
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetService.addBudget(budget);
    }
    
    @PostMapping("/update/account")
    public Account updateAccount(@RequestBody Account account) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        Account currentAccount = myAccountService.getAccount(account.getAccountId());
        if(currentUser.getUsername() == currentAccount.getUser().getUsername()) {
            return myAccountService.updateAccount(account);
        }
        return null;
    }
 
    @PostMapping("/update/item")
    public BudgetItem updateItem(@RequestBody BudgetItem item) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        BudgetItem budgetItem = itemService.getItem(item.getItemId());
        if(currentUser.getUsername() == budgetItem.getBudget().getUser().getUsername()) {
            return itemService.updateItem(item);
        }
        return null;
    }
 
    @PostMapping("/update/budget")
    public Budget updateBudget(@RequestBody Budget budget) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        Budget curBudget = budgetService.getBudgetById(budget.getId());
        if(currentUser.getUsername() == curBudget.getUser().getUsername()) {
            return budgetService.updateBudget(budget);
        }
        return null;
    }
}
