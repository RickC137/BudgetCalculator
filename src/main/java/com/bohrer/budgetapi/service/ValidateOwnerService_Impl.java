package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidateOwnerService_Impl implements ValidateOwnerService {

    @Autowired
    private UserService userService;
    
    @Autowired
    private BudgetService budgetService;

    @Autowired 
    private ItemService itemService;

    @Autowired
    private MyAccountService myAccountService; 

    @Override
    public boolean ownsItem(String username, Long itemId) {
        User user = userService.findByUsername(username);
        BudgetItem item = itemService.getItem(itemId);
        return isOwner(user, item.getBudget().getUser());
    }

    @Override
    public boolean ownsBudget(String username, Long budgetId) {
        User user = userService.findByUsername(username);
        Budget budget = budgetService.getBudgetById(budgetId);
        // TODO Auto-generated method stub
        return isOwner(user, budget.getUser());
    }

    @Override
    public boolean ownsAccount(String username, Long accountId) {
        User user = userService.findByUsername(username);
        Account account = myAccountService.getAccount(accountId);
        return isOwner(user, account.getUser());
    }

    private boolean isOwner(User curUser, User actualUser) {
        if(curUser == null || actualUser == null) {
            return false;
        }
        if(curUser.getUsername().equals(actualUser.getUsername())) {
            return true;
        } else {
            return false;
        }

    }
    
}
