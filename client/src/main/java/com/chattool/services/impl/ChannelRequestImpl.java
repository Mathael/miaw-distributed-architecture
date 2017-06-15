package com.chattool.services.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Channel;
import com.chattool.services.ChannelRequestService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class ChannelRequestImpl implements ChannelRequestService {

    // Current channel list used by the client to communicate
    private final List<Channel> channels = new ArrayList<>();

    @Override
    public Channel create(Channel channel) {
        return ClientApplication.webClient()
                .path("/channel")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(channel))
                .readEntity(Channel.class);
    }

    public List<Channel> getActiveChannels(){
        return channels;
    }

    @Override
    public List<Channel> retrieveAllChannels() {

        final Response response = ClientApplication.webClient()
                .path("/channel")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get(Response.class);

        System.out.println(response.toString());

        return response.readEntity(new GenericType<List<Channel>>(){});
    }
}
