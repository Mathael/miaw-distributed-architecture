package com.interfaces;

import com.model.Account;

/**
 * @author Leboc Philippe.
 */
public interface ChatService {
    // Channels
    boolean joinChannel(String channelId, Account account);

    void exitChannel(String channelId, Account account);

    // chat
    void say(String channelId, String message);

    // Historique
    // TODO

    // Gestion des comptes
    // TODO
}
