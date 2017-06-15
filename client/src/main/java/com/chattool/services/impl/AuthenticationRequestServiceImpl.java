package com.chattool.services.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.services.AuthenticationRequestService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Leboc Philippe.
 */
public class AuthenticationRequestServiceImpl implements AuthenticationRequestService {

    @Override
    public Account login(String username, String password) {
        final Response response = ClientApplication.webClient()
                .path("/auth/login")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(new Account("-1", username, password)));

        return response.readEntity(Account.class);
    }

    @Override
    public String logout() {
        return ClientApplication.webClient()
                .path("/auth/logout")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json("TODO"))
                .readEntity(String.class);
    }

    @Override
    public Account register(Account account) {
        return ClientApplication.webClient()
                .path("/auth/register")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .post(Entity.json(account))
                .readEntity(Account.class);
    }
}
