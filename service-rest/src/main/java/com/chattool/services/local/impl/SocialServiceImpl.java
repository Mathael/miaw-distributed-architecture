package com.chattool.services.local.impl;

import com.chattool.model.Account;
import com.chattool.services.local.AccountingService;
import com.chattool.services.local.SocialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Leboc Philippe.
 */
@Service
public class SocialServiceImpl implements SocialService {

    private final Logger LOGGER = LoggerFactory.getLogger(SocialServiceImpl.class);

    private final ConcurrentHashMap<Account, Account> pendingFriendRequests = new ConcurrentHashMap<>();

    @Autowired
    private AccountingService accountingService;

    @Override
    public boolean sendFriendRequest(String friendAccontId, Account requester) {

        // Requester and requested must be logged in !!!

        final Account friendAccount = accountingService.find(friendAccontId);
        if(friendAccount == null || !accountingService.isLoggedIn(requester)) return false;

        pendingFriendRequests.putIfAbsent(requester, friendAccount);
        return true;
    }

}
