package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.Budget;
import com.bohrer.budgetapi.domain.User;
import com.bohrer.budgetapi.repository.BudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService_Impl implements BudgetService{

    @Autowired
    private BudgetRepository budgetRepository;

    @Override
    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    @Override
    public void removeBudgetById(Long id) {
        budgetRepository.deleteById(id);
    }

    @Override
    public Budget updateBudget(Budget budget) {
        Budget curBudget = budgetRepository.getById(budget.getId());

        if(budget.getUser() != curBudget.getUser()) {
            return null;
        }
        return budgetRepository.save(budget);
    }

    @Override
    public Budget getBudgetById(Long id) {
        return budgetRepository.getById(id);
    }

    @Override
    public Budget getBudgetByMonthAndYear(User user, int month, int year) {
        return budgetRepository.findByUserIdAndMonthAndYear(user.getId(), month, year);
    }
    
}
