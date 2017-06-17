package com.chattool.tasks;

import com.chattool.ClientApplication;
import com.chattool.model.Message;
import com.chattool.services.local.impl.ApplicationScopeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leboc Philippe.
 */
public class FetchMessageTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(FetchMessageTask.class);

    private static final int SLEEP_RATE_IN_MILLIS = 1000;
    private final String channelId;
    private String lastReadMessageId;

    public FetchMessageTask(String channelId, String lastReadMessageId) {
        this.channelId = channelId;
        this.lastReadMessageId = lastReadMessageId;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(SLEEP_RATE_IN_MILLIS);
                final List<Message> messages = new ArrayList<>();

                if(lastReadMessageId == null) {
                    messages.addAll(ClientApplication.chatService
                            .getMessages(channelId));
                } else {
                    messages.addAll(ClientApplication.chatService
                            .getMessagesFromLastRead(channelId, lastReadMessageId));
                }

                if(messages.size() >= 1) {
                    // Display messages
                    messages.forEach(message -> LOGGER.info(String.format("[%s] %s", message.getAuthor(), message.getContent())));

                    final Message lastRead = messages.get(messages.size()-1);
                    this.lastReadMessageId = lastRead.getId();
                    ApplicationScopeService.getInstance().setLastReadMessageId(lastRead.getId());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
