package com.interfaces.authmanager;

import com.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface AuthService extends Remote {
    // Authentication
    Account connect(String username, String password) throws RemoteException;
    void logout(Account account) throws RemoteException;
    Account register(String username, String password) throws RemoteException;

    // Save the data
    void save(Object discussion) throws RemoteException;
}
