package com.chattool.services;

import com.chattool.model.Account;
import com.chattool.model.Channel;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface ChannelRequestService {

    Channel join(String name, Account account);

    Channel create(Channel channel);

    List<Channel> retrieveAllChannels();
}
