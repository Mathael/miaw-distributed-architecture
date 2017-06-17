package com.chattool.services.remote;

import com.chattool.model.Account;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Leboc Philippe.
 */
public interface FriendService extends Remote {
    Account saveNewFriend(Account account, Account friendAccount) throws RemoteException;
    Account getFriends(Account account) throws RemoteException;
}
