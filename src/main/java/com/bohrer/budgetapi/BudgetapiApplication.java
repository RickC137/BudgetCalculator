package com.bohrer.budgetapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BudgetapiApplication {
	/**
	 * This application is mainly used to practice finding and resolving vulnerablities in 
	 * a restful application. DO NOT USE since a lot of the endpoints/services are insecure
	 * by design and are only intended to be used to practice finding bugs for bug bounties.
	 */
	public static void main(String[] args) throws Throwable {
		
		SpringApplication.run(BudgetapiApplication.class, args);
	}

}
