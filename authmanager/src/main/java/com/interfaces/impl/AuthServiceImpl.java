package com.interfaces.impl;

import com.interfaces.authmanager.AuthService;

/**
 * @author Leboc Philippe.
 */
public class AuthServiceImpl implements AuthService {

    @Override
    public Object connect() {
        System.out.println("Ca marche");
        return null;
    }

    @Override
    public void logout() {

    }

    @Override
    public Object register() {
        return null;
    }

    @Override
    public void save(Object discussion) {

    }
}
