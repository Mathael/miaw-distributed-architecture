package com.chattool.services.local.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.services.ChannelRequestService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class ChannelRequestServiceImpl implements ChannelRequestService {

    @Override
    public Channel join(String name, Account account) {
        return ClientApplication.webClient()
                .path("/channel/join/"+name)
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.json(account))
                .readEntity(Channel.class);
    }

    @Override
    public Channel create(Channel channel) {
        return ClientApplication.webClient()
                .path("/channel")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(channel))
                .readEntity(Channel.class);
    }

    @Override
    public List<Channel> retrieveAllChannels() {
        return ClientApplication.webClient()
                .path("/channel")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class)
                .readEntity(new GenericType<List<Channel>>(){});
    }
}
