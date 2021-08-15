package com.bohrer.budgetapi.repository;

import com.bohrer.budgetapi.domain.Budget;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long>{
    Budget findByUserId(Long userid);
    Budget findByUserIdAndMonthAndYear(Long userId, int month, int year);
}
