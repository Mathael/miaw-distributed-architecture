package com.chattool.services.local.impl;

import com.chattool.ServiceRestApplication;
import com.chattool.enums.AccountSearchType;
import com.chattool.model.Account;
import com.chattool.services.local.AccountingService;
import com.chattool.util.SystemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author Leboc Philippe.
 * Use it with @Autowired dependency injection.
 * Note: A service is a Singleton
 */
@Service
public class AccountingServiceImpl implements AccountingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingServiceImpl.class);

    // Online clients are stored here
    private final ConcurrentLinkedDeque<Account> accounts = new ConcurrentLinkedDeque<>();

    @Override
    public Account login(String username, String password) {
        Account account = null;
        try {
            account = ServiceRestApplication.authService.findAccount(username, AccountSearchType.USERNAME);

            // retrieve friends
            ServiceRestApplication.friendService.getFriends(account);

            if(account == null || !account.getPassword().equals(password)) {
                LOGGER.warn(SystemMessage.LOGIN_REQUEST_FAIL);
            }
        } catch (RemoteException e) {
            LOGGER.info(SystemMessage.RMI_REMOTE_FAIL, e);
        }

        // if account is already in online list, just ignore it
        if(!isLoggedIn(account)) accounts.add(account);

        LOGGER.info(SystemMessage.LOGIN_REQUEST_SUCCESS);
        return account;
    }

    @Override
    public void logout(String accountId) {
        final Account account = find(accountId);
        if(account != null) accounts.remove(account);
    }

    @Override
    public boolean isLoggedIn(Account account) {
        final Account knownAccount = find(account);
        return knownAccount != null;
    }

    public Account find(Account account) {
        return find(account.getId());
    }

    public Account find(String accountId) {
        return accounts.stream().filter(data -> data.getId().equals(accountId)).findFirst().orElse(null);
    }

    @Override
    public ConcurrentLinkedDeque<Account> getOnlineList() {
        return new ConcurrentLinkedDeque<>(accounts);
    }
}
