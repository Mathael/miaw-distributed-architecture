package com.chattool.services.local.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.model.Message;
import com.chattool.services.ChatRequestService;
import com.chattool.wrapper.SendMessageWrapper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class ChatRequestServiceImpl implements ChatRequestService {

    @Override
    public boolean say(Account account, Message message) {
        return ClientApplication.webClient()
                .path("/chat")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(new SendMessageWrapper(account, message))).readEntity(Boolean.class);
    }

    @Override
    public List<Message> getMessages(String channelId) {
        return ClientApplication.webClient()
                .path(String.format("/chat/%s", channelId))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Message>>(){});
    }

    @Override
    public List<Message> getMessagesFromLastRead(String channelId, String lastReadMessageId) {
        return ClientApplication.webClient()
                .path(String.format("/chat/%s/%s", channelId, lastReadMessageId))
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<Message>>(){});
    }
}
