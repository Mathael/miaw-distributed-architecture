package com.chattool.services;

import com.chattool.model.Account;

/**
 * @author Leboc Philippe.
 */
public interface ChatService {
    // Channels
    boolean joinChannel(String channelId, Account account);

    void exitChannel(String channelId, Account account);

    // chat
    void say(String channelId, String messageContent, String accountId);
}
