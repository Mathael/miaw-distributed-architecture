package com.chattool.services;

import com.chattool.model.Account;

/**
 * @author Leboc Philippe.
 */
public interface AuthenticationRequestService {
    Account login(String username, String password);
    String logout();
    Account register(Account account);
}
