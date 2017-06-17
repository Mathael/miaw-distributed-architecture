package com.chattool.services.remote.impl;

import com.chattool.services.remote.MessageService;
import com.chattool.model.Account;

import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class MessageServiceImpl implements MessageService {

    @Override
    public void saveHistory(List<Object> messages) throws RemoteException {

    }

    @Override
    public List<Object> retrieveHistory(Account account) throws RemoteException {
        return null;
    }
}
