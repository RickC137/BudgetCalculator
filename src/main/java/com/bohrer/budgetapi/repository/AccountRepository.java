package com.bohrer.budgetapi.repository;

import com.bohrer.budgetapi.domain.Account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
