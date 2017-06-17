package com.chattool.services.remote;

import com.chattool.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface AuthService extends Remote {
    Account register(String username, String password) throws RemoteException;
    boolean remove(String username) throws RemoteException;
    Account findAccount(String username) throws RemoteException;
}
