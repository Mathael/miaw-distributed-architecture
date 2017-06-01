package com.chattool.services;

import com.chattool.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface AuthService extends Remote {
    Account connect(String username, String password) throws RemoteException;
    void logout(Account account) throws RemoteException;
    Account register(String username, String password) throws RemoteException;
    boolean remove(String username) throws RemoteException;
}
