package com.bohrer.budgetapi.repository;

import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.BudgetItem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetItemRepository extends JpaRepository<BudgetItem, Long>{
    
}
