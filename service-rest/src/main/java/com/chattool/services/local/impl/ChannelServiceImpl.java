package com.chattool.services.local.impl;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.services.local.AccountingService;
import com.chattool.services.local.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Georges.
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Autowired
    private AccountingService accountingService;

    private final List<Channel> channels = new ArrayList<>();

    public Channel create(Channel channel) {
        final Channel sameNameChannel = channels
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(channel.getName()))
                .findFirst().orElse(null);
        if(sameNameChannel != null) {
            LOGGER.warn("IMPLEMENT ME"); // TODO
            return null;
        }

        channel.setId(UUID.randomUUID().toString());
        channels.add(channel);
        return channel;
    }

    @Override
    public List<Channel> findAll() {
        return channels;
    }

    @Override
    public Channel join(String channelName, Account account) {

        if(!accountingService.isLoggedIn(account)) return null;

        final Channel channel = findChannelByName(channelName);
        if(channel == null) return null;

        channel.getAccounts().add(account);
        return channel;
    }

    @Override
    public void quit(String channelName, Account account) {

        if(!accountingService.isLoggedIn(account)) return;

        final Channel channel = findChannelByName(channelName);
        if(channel == null) return;

        channel.getAccounts().remove(account);
    }

    private Channel findChannelByName(String channelName) {
        return channels
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(channelName))
                .findFirst()
                .orElse(null);
    }

    private Channel findChannel(String channelId) {
        return channels
                .stream()
                .filter(c -> c.getId().equals(channelId))
                .findFirst().orElse(null);
    }
}
