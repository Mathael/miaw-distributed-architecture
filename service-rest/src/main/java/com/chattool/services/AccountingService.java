package com.chattool.services;

import com.chattool.model.Account;

/**
 * @author Leboc Philippe.
 */
public interface AccountingService {
    Account login(String username, String password);
    void logout(String accountId);
    boolean isLoggedIn(Account account);
    Account find(Account account);
    Account find(String id);
}
