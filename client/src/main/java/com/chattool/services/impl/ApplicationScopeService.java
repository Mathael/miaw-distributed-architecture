package com.chattool.services.impl;

import com.chattool.model.Account;
import com.chattool.model.Channel;
import com.chattool.tasks.FetchMessageTask;
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
    private String lastReadMessageId = null;

    private Thread readMessageTask;

    private ApplicationScopeService() {}

    public void startMessageReaderTask(String lastReadMessageId) {
        if(this.readMessageTask != null) stopAllTasks();

        this.lastReadMessageId = lastReadMessageId;
        this.readMessageTask = new Thread(new FetchMessageTask(activeChannel.getId(), this.lastReadMessageId));
        this.readMessageTask.start();
    }

    public void stopAllTasks() {
        this.getReadMessageTask().interrupt();
        this.setReadMessageTask(null);
    }
}
