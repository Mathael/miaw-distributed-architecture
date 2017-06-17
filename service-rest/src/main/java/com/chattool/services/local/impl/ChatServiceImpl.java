package com.chattool.services.local.impl;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.model.Message;
import com.chattool.services.local.ChannelService;
import com.chattool.services.local.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Leboc Philippe.
 */
@Service
public class ChatServiceImpl implements ChatService {

    private final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

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

        channel.getMessages().add(new Message(UUID.randomUUID().toString(), message.getChannelId(), message.getContent(), acc.getUsername(), System.currentTimeMillis()));
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

        if(channel != null)
        {
            boolean startRecording = lastReadMessageId == null;
            final List<Message> sortedMessagesList = channel.getMessages()
                    .stream().sorted(Comparator.comparing(Message::getTimestamp))
                    .collect(Collectors.toList());

            for (Message message : sortedMessagesList) {
                if(startRecording)  messages.add(message);
                if(message.getId().equals(lastReadMessageId)) startRecording = true;
            }
        } else {
            LOGGER.warn("Quelqu'un essaie d'accéder au channel : " + channelId + " -> Channel is null"); // TODO
        }
        return messages;
    }
}
