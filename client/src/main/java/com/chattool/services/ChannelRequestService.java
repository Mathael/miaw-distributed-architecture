package com.chattool.services;

import com.chattool.model.Channel;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface ChannelRequestService {

    Channel create(Channel channel);

    List<Channel> getActiveChannels();

    List<Channel> retrieveAllChannels();
}
