package com.chattool.services;

import com.chattool.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface MessageService extends Remote {
    void saveHistory(List<Object> messages) throws RemoteException;
    List<Object> retrieveHistory(Account account) throws RemoteException;
}
