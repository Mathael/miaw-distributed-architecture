package com.chattool.services.impl;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.ChannelService;
import com.chattool.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Leboc Philippe.
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChannelService channelService;

    @Override
    public boolean say(Account acc, Message message) {
        final Channel channel = channelService.findAll()
                .stream().filter(chan -> {
                    if(chan.getId().equals(message.getChannelId())){
                        final Account account = chan.getAccounts()
                                .stream()
                                .filter(user -> user.getId().equals(acc.getId()))
                                .findFirst().orElse(null);
                        if(account != null) return true;
                    }
                    return false;
                }).findFirst().orElse(null);

        if(channel == null) return false;

        channel.getMessages().add(new Message(UUID.randomUUID().toString(), message.getChannelId(), message.getContent(), acc.getUsername()));
        return true;
    }

    @Override
    public List<Message> getMessages(String channelId) {
        return getMessages(channelId, null);
    }

    @Override
    public List<Message> getMessages(String channelId, String lastReadMessageId) {
        final List<Message> messages = new ArrayList<>();
        final Channel channel = channelService
                .findAll()
                .stream()
                .filter(chan -> chan.getId().equals(channelId))
                .findFirst()
                .orElse(null);

        if(channel != null) {
            boolean startRecording = lastReadMessageId == null;
            for (Message message : channel.getMessages()) {
                if(startRecording || message.getId().equals(lastReadMessageId)) {
                    startRecording = true;
                    messages.add(message);
                }
            }
        }
        return messages;
    }
}
