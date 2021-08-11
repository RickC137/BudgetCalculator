package com.bohrer.budgetapi.service;

import com.bohrer.budgetapi.domain.BudgetItem;

public interface ItemService {
    BudgetItem addItem(BudgetItem item);
    void deleteItemById(Long id);
    BudgetItem updateItem(BudgetItem item);
    BudgetItem getItem(Long id);
}
