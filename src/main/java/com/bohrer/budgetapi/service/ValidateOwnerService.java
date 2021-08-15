package com.bohrer.budgetapi.service;

public interface ValidateOwnerService {
    boolean ownsItem(String username, Long itemId);
    boolean ownsBudget(String username, Long budgetId);
    boolean ownsAccount(String username, Long accountId);
}
