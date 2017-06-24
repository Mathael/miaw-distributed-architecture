package com.chattool.services.remote;

import com.chattool.enums.AccountSearchType;
import com.chattool.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface AuthService extends Remote {

    /**
     * @param username The account username
     * @param password The account password
     * @return The newly created account if successful, null otherwise
     * @throws RemoteException
     */
    Account register(String username, String password) throws RemoteException;

    /**
     * @param username The given account username
     * @return True if operation was successful, false otherwise
     * @throws RemoteException
     */
    boolean remove(String username) throws RemoteException;

    /**
     * @param username The given username corresponding to the account to be retrieved
     * @return The corresponding account if found, null otherwise
     * @throws RemoteException
     */
    Account findAccount(String username, AccountSearchType type) throws RemoteException;
}
