package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.BudgetItem;
import com.bohrer.budgetapi.repository.BudgetItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService_Impl implements ItemService{
    @Autowired
    private BudgetItemRepository budgetItemRepository;

    @Override
    public BudgetItem addItem(BudgetItem item) {
        return budgetItemRepository.save(item);
    }

    @Override
    public void deleteItemById(Long id) {
        budgetItemRepository.deleteById(id);
        
    }

    @Override
    public BudgetItem updateItem(BudgetItem item) {
        return budgetItemRepository.save(item);
        
    }

    @Override
    public BudgetItem getItem(Long id) {
        return budgetItemRepository.getById(id);
    }
    
}
