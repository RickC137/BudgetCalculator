package com.bohrer.budgetapi;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.AccountRepository;
import com.bohrer.budgetapi.repository.BudgetItemRepository;
import com.bohrer.budgetapi.repository.BudgetRepository;
import com.bohrer.budgetapi.repository.UserRepository;
import com.bohrer.budgetapi.service.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BudgetapiApplication {

    // set 	some initial values
	@Autowired
	UserRepository repository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	@Autowired
	BudgetRepository budgetRepository;

	@Autowired
	BudgetItemRepository budgetItemRepository;

	public static void main(String[] args) {
		
		SpringApplication.run(BudgetapiApplication.class, args);
	}

}
