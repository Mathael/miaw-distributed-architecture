package com.interfaces.impl;

import com.interfaces.ChatService;
import com.model.Account;
import com.model.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class ChatServiceImpl implements ChatService {

    private HashMap<Channel, List<Account> > channels;

    public ChatServiceImpl() {
        this.channels = new HashMap<>();
    }

    @Override
    public boolean joinChannel(String channelId, Account account) {

        final Channel channel = channels
                .keySet()
                .stream()
                .filter(c -> c.getChannelId().equals(channelId))
                .findFirst().orElse(null);

        if (channel != null) {
            final List<Account> accounts = channels.get(channel);
            if(accounts != null) {
                accounts.add(account);
                return true;
            }
        }

        return false;
    }

    @Override
    public void exitChannel(String channelId, Account account) {

        final Channel channel = channels
                .keySet()
                .stream()
                .filter(c -> c.getChannelId().equals(channelId))
                .findFirst().orElse(null);

        if(channel != null) {
           channels.
        }

    }

    @Override
    public void say(String channelId, String message) {

    }

}
