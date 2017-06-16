package com.chattool.services;

import com.chattool.model.Account;
import com.chattool.model.Message;

import java.util.List;

/**
 * @author Leboc Philippe.
 */
public interface ChatRequestService {
    boolean say(Account account, Message message);
    List<Message> getMessages(String channelId);
    List<Message> getMessagesFromLastRead(String channelId, String lastReadMessageId);
}
