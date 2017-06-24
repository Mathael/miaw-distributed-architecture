package com.chattool.services.local;

import com.chattool.model.Account;

import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Leboc Philippe.
 */
public interface AccountingService {

    /**
     * @param username  The username
     * @param password  The password
     * @return          The Account corresponding to the given username and password parameters
     */
    Account login(String username, String password);

    /**
     * @param accountId The Account identifier
     */
    void logout(String accountId);

    /**
     * @param account The Account to be check
     * @return        true is the given account is logged in, false otherwise
     */
    boolean isLoggedIn(Account account);

    /**
     * @param account   The Account to be found
     * @return          The found Account, null otherwise
     */
    Account find(Account account);

    /**
     * @param id        The Account identifier of Account to be find
     * @return          The found Account, null otherwise
     */
    Account find(String id);

    /**
     * @return The online users list
     */
    ConcurrentLinkedDeque<Account> getOnlineList();
}
