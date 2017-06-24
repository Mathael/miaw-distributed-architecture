package com.chattool.services.local;

import com.chattool.model.Account;

/**
 * @author Leboc Philippe.
 */
public interface SocialService {

    /**
     * @param friendAccontId    The user to be requested
     * @param requester         The requester Account
     * @return                  True if request was send successful, false otherwise
     */
    boolean sendFriendRequest(String friendAccontId, Account requester);

}
