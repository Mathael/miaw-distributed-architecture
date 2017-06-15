package com.chattool.services.impl;

import com.chattool.ServiceRestApplication;
import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.AccountingService;
import com.chattool.services.ChannelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
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
            LOGGER.info("IMPLEMENT ME"); // TODO
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
    public Channel join(String channelId, Account account) {

        if(!accountingService.isLoggedIn(account)) return null;

        final Channel channel = findChannel(channelId);
        if(channel == null) return null;

        channel.getAccounts().add(account);
        return channel;
    }

    @Override
    public void exit(String channelId, Account account) {

        if(!accountingService.isLoggedIn(account)) return;

        final Channel channel = findChannel(channelId);
        if(channel == null) return;

        channel.getAccounts().remove(account);
    }

    @Override
    public void say(String channelId, String messageContent, String accountId) {

        // TODO: handle me
        // TODO: re-impl me
        //if(!accountingService.isLoggedIn(account)) return false;

        try {
            final Account account = ServiceRestApplication.authService.findAccount(accountId);
            if(account == null) return;

            final Channel channel = findChannel(channelId);
            if(channel == null) return;

            final Message message = new Message(UUID.randomUUID().toString(), messageContent, account);
            channel.getMessages().add(message);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Channel findChannel(String channelId) {
        return channels
                .stream()
                .filter(c -> c.getId().equals(channelId))
                .findFirst().orElse(null);
    }
}
