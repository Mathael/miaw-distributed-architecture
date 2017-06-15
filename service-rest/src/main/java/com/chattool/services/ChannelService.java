package com.chattool.services;

import com.chattool.model.Account;
import com.chattool.model.Channel;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface ChannelService {

    /**
     * @param channel The channel to be created
     * @return The newly created channel with an unique identifier. null if created fail.
     */
    Channel create(Channel channel);

    /**
     * @return all existing Channels
     */
    List<Channel> findAll();

    /**
     * @param channelName The channel name
     * @param account Account who want to join the channel
     * @return newly joined channel information
     */
    Channel join(String channelName, Account account);

    /**
     * @param channelName The channel name
     * @param account Account who want to exit from the channel
     */
    void quit(String channelName, Account account);
}
