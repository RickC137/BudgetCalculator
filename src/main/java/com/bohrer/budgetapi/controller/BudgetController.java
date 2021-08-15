package com.bohrer.budgetapi.controller;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.service.BudgetService;
import com.bohrer.budgetapi.service.ItemService;
import com.bohrer.budgetapi.service.MyAccountService;
import com.bohrer.budgetapi.service.UserService;
import com.bohrer.budgetapi.service.ValidateOwnerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BudgetController {
 
    @Autowired
    private BudgetService budgetService;
 
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private MyAccountService myAccountService;
    
    @Autowired
    private UserService userService;

    @Autowired
    private ValidateOwnerService validateOwnerService;

    /**
     * Gets
     */

    @GetMapping("/get/account")
    public Account getAccount(Authentication auth, Long id) {
        if(validateOwnerService.ownsAccount(((UserDetails)auth.getPrincipal()).getUsername(), id)) {
            return myAccountService.getAccount(id);
        }
        return null;
    }

    @GetMapping("/get/budget")
    public Budget getBudget(Authentication auth, Long id) {
        if(validateOwnerService.ownsBudget(((UserDetails)auth.getPrincipal()).getUsername(), id)) {
            return budgetService.getBudgetById(id);
        }
        return null;
    }
    
    @GetMapping("/get/item")
    public BudgetItem getItem(Authentication auth, Long id) {
        if(validateOwnerService.ownsItem(((UserDetails)auth.getPrincipal()).getUsername(), id)) {
            return itemService.getItem(id);
        }
        return null;
    }

    @GetMapping("/get/monthly/budget")
    public Budget getBudgetByMonth(Authentication auth, int monthId, int year) {
        String username = ((UserDetails)auth.getPrincipal()).getUsername();
        User user = userService.findByUsername(username);
        return budgetService.getBudgetByMonthAndYear(user, monthId, year);
    }

    /**
     * Deletes
     */

    @GetMapping("/delete/account")
    public String deleteAccount(Authentication auth, Long id) {
        if(validateOwnerService.ownsAccount(((UserDetails)auth.getPrincipal()).getUsername(), id)) {
            myAccountService.removeAccountById(id);
            return "Removed Item";
        }
        return "Error";
    }

    @GetMapping("/delete/budget")
    public String deleteBudget(Authentication auth, Long id) {
        if(validateOwnerService.ownsBudget(((UserDetails)auth.getPrincipal()).getUsername(), id)) {
            budgetService.removeBudgetById(id);
            return "Removed Item";
        }
        return "Error";
    }

    @GetMapping("/delete/item")
    public String deleteItem(Authentication auth, Long itemId) {
        if(validateOwnerService.ownsItem(((UserDetails)auth.getPrincipal()).getUsername(), itemId)) {
            itemService.deleteItemById(itemId);
            return "Removed Item";
        }
        return "Error";
    }

    /**
     * Create
     */

    @PostMapping("/create/account")
    public Account createAccount(Authentication auth, @RequestBody Account account) {
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        User user = userService.findByUsername(currentUser.getUsername());
        account.setUser(user);
        return myAccountService.addAccount(account);
    }

    @PostMapping("/create/budget")
    public Budget createBudget(Authentication auth, @RequestBody Budget budget) {
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        User user = userService.findByUsername(currentUser.getUsername());
        budget.setUser(user);
        return budgetService.addBudget(budget);
    }

    @PostMapping("/create/item")
    public BudgetItem createItem(Authentication auth, @RequestBody BudgetItem item, Long budgetId) {
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        if(validateOwnerService.ownsBudget(currentUser.getUsername(), budgetId)) {
            Budget budget = budgetService.getBudgetById(budgetId);
            item.setBudget(budget);
            return itemService.addItem(item);
        } 
        return null;
    }
    
    /**
     * Updates
     */

    @PostMapping("/update/account")
    public Account updateAccount(Authentication auth, @RequestBody Account account) {
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        Account currentAccount = myAccountService.getAccount(account.getAccountId());
        if(currentUser.getUsername() == currentAccount.getUser().getUsername()) {
            return myAccountService.updateAccount(account);
        }
        return null;
    }
 
    @PostMapping("/update/budget")
    public Budget updateBudget(Authentication auth, @RequestBody Budget budget) {
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        if(validateOwnerService.ownsBudget(currentUser.getUsername(), budget.getId())) {
            return budgetService.updateBudget(budget);
        }
        return null;
    }

    @PostMapping("/update/item")
    public BudgetItem updateItem(Authentication auth, @RequestBody BudgetItem item) {
        UserDetails currentUser = (UserDetails)auth.getPrincipal();
        BudgetItem budgetItem = itemService.getItem(item.getItemId());
        if(currentUser.getUsername() == budgetItem.getBudget().getUser().getUsername()) {
            return itemService.updateItem(item);
        }
        return null;
    }
 
}
