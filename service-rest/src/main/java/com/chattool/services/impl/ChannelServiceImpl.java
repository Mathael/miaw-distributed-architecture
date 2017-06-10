package com.chattool.services.impl;

import com.chattool.ServiceRestApplication;
import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.ChannelService;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Georges.
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    private final HashMap<Channel, List<Account>> channels;

    public ChannelServiceImpl() {
        this.channels = new HashMap<>();
    }

    @Override
    public boolean joinChannel(String channelId, Account account) {

        final Channel channel = findChannel(channelId);
        if(channel == null) return false;

        final List<Account> accounts = channels.get(channel);
        if (accounts == null) return false;

        accounts.add(account);
        return true;
    }

    @Override
    public void exitChannel(String channelId, Account account) {

        final Channel channel = findChannel(channelId);
        if(channel == null) return;

        final List<Account> accounts = channels.get(channel);
        accounts.remove(account);
    }

    @Override
    public void say(String channelId, String messageContent, String accountId) {
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
                .keySet()
                .stream()
                .filter(c -> c.getId().equals(channelId))
                .findFirst().orElse(null);
    }
}
