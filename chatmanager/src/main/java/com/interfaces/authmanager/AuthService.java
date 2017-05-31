package com.interfaces.authmanager;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface AuthService extends Remote {
    // Authentication
    Object connect() throws RemoteException;
    void logout() throws RemoteException;
    Object register() throws RemoteException;

    // Save the data
    void save(Object discussion) throws RemoteException;
}
