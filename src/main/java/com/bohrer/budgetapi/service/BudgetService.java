package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.Budget;

public interface BudgetService {
    Budget addBudget(Budget budget);
    void removeBudgetById(Long id);
    Budget updateBudget(Budget budget);
    Budget getBudgetById(Long id);
}
