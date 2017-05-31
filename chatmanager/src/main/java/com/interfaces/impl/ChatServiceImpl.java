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

    private HashMap<Channel, Account> channels;

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
            channels.put(channel, account);
            return true;
        }

        return false;
    }

    @Override
    public void exitChannel(String channelId) {

        

    }

    @Override
    public void say(String channelId, String message) {

    }

}
