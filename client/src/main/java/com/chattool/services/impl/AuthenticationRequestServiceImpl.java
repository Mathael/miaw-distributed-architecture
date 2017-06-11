package com.chattool.services.impl;

import com.chattool.ClientApplication;
import com.chattool.model.Account;
import com.chattool.services.AuthenticationRequestService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Leboc Philippe.
 */
public class AuthenticationRequestServiceImpl implements AuthenticationRequestService {

    @Override
    public Account login(String username, String password) {
        final Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", username);
        requestBody.put("password", password);
        return ClientApplication.webClient()
                .path("/chatAuthService/login")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .post(Entity.xml(requestBody))
                .readEntity(Account.class);
    }

    @Override
    public String logout() {
        return ClientApplication.webClient()
                .path("/chatAuthService/logout")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .post(Entity.xml("TODO"))
                .readEntity(String.class);
    }

    @Override
    public Account register(Account account) {
        return ClientApplication.webClient()
                .path("/chatAuthService/login")
                .request()
                .accept(MediaType.APPLICATION_XML)
                .post(Entity.xml(account))
                .readEntity(Account.class);
    }
}
