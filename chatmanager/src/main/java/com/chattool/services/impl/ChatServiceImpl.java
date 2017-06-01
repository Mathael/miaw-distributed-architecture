package com.chattool.services.impl;

import com.chattool.model.Message;
import com.chattool.services.AuthService;
import com.chattool.services.ChatService;
import com.chattool.model.Account;
import com.chattool.model.Channel;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author Lucas Georges.
 */
public class ChatServiceImpl implements ChatService {

    private HashMap<Channel, List<Account>> channels;
    private AuthService authService;

    public ChatServiceImpl(AuthService authService) {
        channels = new HashMap<>();
        this.authService = authService;
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
            if (accounts != null) {
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

        if (channel != null) {
            final List<Account> accounts = channels.get(channel);
            accounts.remove(account);
        }
    }

    @Override
    public void say(String channelId, String messageContent, String accountId) {
        try {
            final Account account = authService.findAccount(accountId);
            if(account == null) return;

            final Channel channel = channels
                    .keySet()
                    .stream()
                    .filter(c -> c.getChannelId().equals(channelId))
                    .findFirst().orElse(null);

            final Message message = new Message(UUID.randomUUID().toString(), messageContent, account);
            channel.getChannelMessageList().add(message);

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
