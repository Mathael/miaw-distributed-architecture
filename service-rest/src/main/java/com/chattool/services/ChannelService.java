package com.chattool.services;

import com.chattool.model.Account;
import com.chattool.model.Channel;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface ChannelService {

    /**
     * @return all existing Channels
     */
    List<Channel> findAll();

    /**
     * @param id The channel identifier
     * @param account Account who want to join the channel
     * @return newly joined channel information
     */
    Channel join(String id, Account account);

    /**
     * @param id The channel identifier
     * @param account Account who want to exit from the channel
     */
    void exit(String id, Account account);

    /**
     *
     * @param id The channel identifier
     * @param messageContent
     * @param accountId The account identifier
     */
    void say(String id, String messageContent, String accountId);
}
