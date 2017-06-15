package com.chattool.services.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.model.Message;
import com.chattool.services.ChatRequestService;
import com.chattool.wrappers.SendMessageWrapper;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

/**
 * @author Leboc Philippe.
 */
public class ChatRequestServiceImpl implements ChatRequestService {

    @Override
    public boolean say(Account account, Message message) {
        //final Gson gson = new Gson();
        //System.out.println("{ \"account\":" + gson.toJson(account) + ", \"message\":" + gson.toJson(message) + " }");
        //final String jsonStr = "{ \"account\":" + gson.toJson(account) + ", \"message\":" + gson.toJson(message) + " }";

        return ClientApplication.webClient()
                .path("/chat")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(new SendMessageWrapper(account, message))).readEntity(Boolean.class);
    }
}
