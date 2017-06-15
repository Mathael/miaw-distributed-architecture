package com.chattool.services.impl;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 * Singleton class that handle all Application Scope attributes
 */
@Data
public class ApplicationScopeService {
    private static ApplicationScopeService _instance = new ApplicationScopeService();

    public static ApplicationScopeService getInstance() {
        return _instance;
    }

    private Account account = null;
    private Channel activeChannel = null;
    private List<Channel> knownChannels = new ArrayList<>();

    private ApplicationScopeService() {}
}
