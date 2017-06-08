package com.chattool.services;

import com.chattool.model.Account;

/**
 * @author Leboc Philippe.
 */
public interface AccountingService {
    Account login(String username, String password);
    String logout(Account account);
}
