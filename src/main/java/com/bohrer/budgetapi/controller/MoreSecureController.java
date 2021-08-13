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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MoreSecureController {
    @Autowired
    private UserService userService;

    @Autowired
    private MyAccountService myAccountService;

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private ItemService itemService;




    @GetMapping("/updateUser")
    User updateUser(@RequestParam("user") String user, @RequestParam("newPass") String password, @RequestParam("curPass") String curPass) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        if(currentUser.getUsername().equals(user)) {
            return userService.updateUserPassword2(user, curPass, password);
        }
       return null;//myUserDetailsService.updateUserPassword2(user, password, );
    }

    @GetMapping("/updateAccount")
    Account updateAccount(@RequestBody Account account) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        Account currentAccount = myAccountService.getAccount(account.getAccountId());
        if(currentUser.getUsername() == currentAccount.getUser().getUsername()) {
            return myAccountService.updateAccount(account);
        }
        return null;
    }

    @GetMapping("/updateItem")
    BudgetItem updateItem(@RequestBody BudgetItem item) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        BudgetItem budgetItem = itemService.getItem(item.getItemId());
        if(currentUser.getUsername() == budgetItem.getBudget().getUser().getUsername()) {
            return itemService.updateItem(item);
        }
        return null;
    }

    @GetMapping("/updateBudget")
    Budget updateBudget(@RequestBody Budget budget) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        Budget curBudget = budgetService.getBudgetById(budget.getId());
        if(currentUser.getUsername() == curBudget.getUser().getUsername()) {
            return budgetService.updateBudget(budget);
        }
        return null;
    }

}
