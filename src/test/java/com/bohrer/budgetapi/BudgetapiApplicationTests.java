package com.bohrer.budgetapi;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.bohrer.budgetapi.domain.Account;
import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.AccountRepository;
import com.bohrer.budgetapi.repository.BudgetItemRepository;
import com.bohrer.budgetapi.repository.BudgetRepository;
import com.bohrer.budgetapi.repository.UserRepository;
import com.bohrer.budgetapi.service.MyUserDetailsService;
import com.bohrer.budgetapi.service.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
class BudgetapiApplicationTests {

	@Autowired
	UserRepository repository;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	UserService userService;

	@Autowired
	BudgetRepository budgetRepository;

	@Autowired
	BudgetItemRepository budgetItemRepository;

	@Test
	void contextLoads() {
		User user = new User("testing","test1");
		repository.save(user);
		User testUser = repository.findByUsername("testing");
		assertNotNull(testUser.getUsername());
	}

	@Test
	void accountTest() {
		contextLoads();
		User testUser = repository.findByUsername("testing");
		Account account = testUser.getAccount();
		assertNull(account);
		account = new Account(new java.sql.Date(0), new java.sql.Date(1), testUser);
		testUser.setAccount(account);
		repository.save(testUser);
		accountRepository.save(account);
		testUser = repository.findByUsername("testing");
		Account newAccount = testUser.getAccount();
		assertNotNull(newAccount);
	}

	@Test
	void updateUserPassword() {
		contextLoads();
		User testUser = repository.findByUsername("testing");
		User newPass = userService.updateUserPassword(testUser.getUsername(), "ChangedToSomething");
		assertNotEquals("test1", newPass.getPassword());
	}

	@Test
	void updateUserPassword2() {
		contextLoads();
		User test = repository.findByUsername("testing");
		User newPass = userService.updateUserPassword2(test.getUsername(), "test1", "changedIt");
		assertNotEquals("test1", newPass.getPassword());
	}

	@Test
	void testBudgets() {
		contextLoads();
		User test = repository.findByUsername("testing");
		Budget budget = new Budget();
		budget.setMonth(1);
		budget.setYear(2021);
		Set<Budget> sets = new HashSet<Budget>();
		sets.add(budget);
		test.setBudgets(sets);
		repository.save(test);
		budget.setUser(test);
		budgetRepository.save(budget);
		Budget curBudget = budgetRepository.getById(budget.getId());
		assertNotNull(curBudget);
		assertNotNull(curBudget.getUser());
	}

	@Test
	void testBudgetItems() {
		testBudgets();
		List<Budget> test = budgetRepository.findAll();
		for(Budget budget: test) {
			BudgetItem item = new BudgetItem();
			item.setAmount(1000);
			item.setTitle("Bitcoin investment"); // nope
			item.setIncome(false);
			Set<BudgetItem> items = new HashSet<>();
			items.add(item);
			item.setBudget(budget);
			budget.setItems(items);
			budgetRepository.save(budget);
			budgetItemRepository.save(item);
		}
		Budget budget = budgetRepository.findAll().get(0);
		assertNotNull(budget);
		assertNotNull(budget.getItems());
	}

}
