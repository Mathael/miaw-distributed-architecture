package com.chattool.services.impl;

import com.chattool.ServiceRestApplication;
import com.chattool.model.Account;
import com.chattool.services.AccountingService;
import com.chattool.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
@Service
public class AccountingServiceImpl implements AccountingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountingServiceImpl.class);

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account login(String username, String password) {
        Account account = null;
        try {
            account = ServiceRestApplication.authService.findAccount(username);

            if(account == null || !account.getPassword().equals(password)) {
                LOGGER.warn(Message.LOGIN_REQUEST_FAIL);
            }

        } catch (RemoteException e) {
            LOGGER.info(Message.RMI_REMOTE_FAIL, e);
        }

        // User has logged in : store in Online Accounts List
        //onlineAccounts.add(account);
        LOGGER.info(Message.LOGIN_REQUEST_SUCCESS);
        return account;
    }

    @Override
    public String logout(Account account) {
        return null;
    }
}
