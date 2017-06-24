package com.chattool.services.remote;

import com.chattool.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface FriendService extends Remote {

    /**
     * @param account           The Account who has a new friend
     * @param friendAccount     The friend Account
     * @return                  The Account updated with the added friend
     * @throws RemoteException
     */
    Account saveNewFriend(Account account, Account friendAccount) throws RemoteException;

    /**
     * @param account           The Account who want to see his friend list
     * @return                  The updated Account with his entire friend list
     * @throws RemoteException
     */
    Account getFriends(Account account) throws RemoteException;
}
