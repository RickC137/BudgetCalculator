package com.bohrer.budgetapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BudgetapiApplicationTests {

	@Autowired
	UserRepository repository;

	@Test
	void contextLoads() {
		User user = new User("testing","test1");
		repository.save(user);
		User testUser = repository.findByUsername("testing");
		System.out.println(testUser.toString());
		assertNotNull(testUser);
	}

}
