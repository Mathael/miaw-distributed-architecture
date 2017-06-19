package com.chattool.services.local.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.services.SocialRequestService;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class SocialRequestServiceImpl implements SocialRequestService {

    @Override
    public List<String> getOnlineList() {
        return ClientApplication.webClient()
                .path("/social/online")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<String>>(){});
    }

    @Override
    public List<String> showOnlineFriends(Account account) {
        return ClientApplication.webClient()
                .path("/social/onlinefriend")
                .queryParam("accountId", account.getId())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON)
                .get()
                .readEntity(new GenericType<List<String>>(){});
    }
}
