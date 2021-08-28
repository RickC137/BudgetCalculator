package com.bohrer.budgetapi.controller;

import java.util.Optional;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.BudgetItemRepository;
import com.bohrer.budgetapi.securingweb.IDORUtil;
import com.bohrer.budgetapi.service.ItemService;
import com.bohrer.budgetapi.service.MyAccountService;
import com.bohrer.budgetapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/idor")
public class IdorVulnController {
    /**
     * This controller will serve as a testing ground
     * for idor related vulnerabilities. For now it
     * will contain some basic level idor bugs to test on,
     * but will contain at a later date some real world examples
     */

    @Autowired
    private ItemService itemService;

    @Autowired
    private BudgetItemRepository budgetItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MyAccountService myAccountService;

    /**
     * This endpoint will return by id without validating user roles
     * or permissions to view the object. 
     */
    @GetMapping("/getItemById")
    public BudgetItem getItem(@RequestParam Long id) {
        return itemService.getItem(id); 
    }

    // insecure due to no auth
    @GetMapping("user")
    public User getUser(@RequestParam String username) {
        return userService.findByUsername(username);
    }
    /**
     * Deletes account by account id
     * no checking that the user is allowed/can delete this account is made
     * 
     */

    @GetMapping("/delete/account/")
    public String deleteAccount(Long id) {
        myAccountService.removeAccountById(id);
        return "Removed Item";
    }

    // no validation that the user assigned to this account is
    // the current user
    @PostMapping("/create/account")
    public Account createAccount(@RequestBody Account account) {
        // any user can create an account for another user
        return myAccountService.addAccount(account);
    }

    /**
     * Any user is able to update any account by updating the id field in the
     * account object
     */
    @PostMapping("/update/account")
    public Account updateAccount(@RequestBody Account account) {
        return myAccountService.updateAccount(account);
    }


    /**
     * This endpoint is more secure since it only takes hashed values for the id
     * There is still the issue if somewhere ids are leaked in which case a user
     * would be able to find out the hashed value given time.
     * All ids would be needed to be hashed before sending back to users
     */
    @GetMapping("/getItemByHashedId") 
    public BudgetItem getItemByHash(@RequestParam String hashId) {
        Optional<BudgetItem> item = budgetItemRepository.findAll().stream().filter(m->{
            boolean match;
            try{
                String frontEndId = IDORUtil.computeFrontEndIdentifier(Long.toString(m.getBudget().getId()));
                match = frontEndId.equals(hashId);
            } catch(Exception e) {
                match = false;
            }
            return match;
        }).findFirst();

        return item.get();
    }
    
}
